package com.demo.controller;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.demo.bean.SourceBean;
import com.demo.dao.SourceDao;



/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SourceBean sourceBean = new SourceBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
		int Uid = (int)session.getAttribute("Userid");
		int cview = 0;
		int cdownload = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String uploadtime = sdf.format(date);
		sourceBean.setSdate(uploadtime);
		sourceBean.setViewcount(cview);
		sourceBean.setDlcount(cdownload);
        boolean tag = false;
        Map<String,String> formmap = new HashMap<String, String>();
        PrintWriter out = response.getWriter();  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
        if(!isMultipart){  
            throw new RuntimeException("请检查您的表单的enctype属性，确定是multipart/form-data");  
        }  
        DiskFileItemFactory diskfile = new DiskFileItemFactory();  
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskfile);   
        servletFileUpload.setFileSizeMax(500*1024*1024);		//设置单个文件上传的大小  
        servletFileUpload.setSizeMax(1024*1024*1024);			//多文件上传时总大小限制  
        List<FileItem> items = null;  
        try {  
            items = servletFileUpload.parseRequest(request);  
        }catch(FileUploadBase.FileSizeLimitExceededException e) {  
            out.write("上传的文件超出了500M");  
            return;  
        }catch(FileUploadBase.SizeLimitExceededException e){  
            out.write("总文件超出了1G");  
            return;  
        }catch (FileUploadException e) {  
            e.printStackTrace();  
            throw new RuntimeException("上传内容解析失败，请重新试一下");  
        }  
        //处理请求内容  
        if(items!=null){  
            for(FileItem item:items){  
                if(item.isFormField()){
                	Map<String,String> map = new HashMap<String,String>();
                	String fieldname = null;
                	String fieldvalue = null;
                    map = processFormField(item);						//表单文本域 
                    for(Entry<String,String> entry : map.entrySet()) {
                    	fieldname = entry.getKey();
                    	fieldvalue = entry.getValue();
                    }
                    formmap.put(fieldname, fieldvalue);
                }else{  
                    tag = processUploadField(item);  			//表单文件域
                }  
            }  
        }  
        if(tag) {
            for(Entry<String, String> entry : formmap.entrySet()) {
            	switch (entry.getKey()) {
    				case "inname": sourceBean.setDescribe(entry.getValue());break;
    				case "insort": sourceBean.setSourcesort(entry.getValue());break;
    				case "inabstract" : sourceBean.setIntro(entry.getValue());break;
    			}
            }
            SourceDao sourceDao = new SourceDao();
            boolean flag = false;
            try {
				flag = sourceDao.uploadfile(sourceBean, Uid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(flag) {
//            	out.write("上传成功！");
            	response.sendRedirect("../../../view/uploadfile.jsp?fileup=yes");
            }else {
            	out.write("上传数据库失败！");
			}  
        }else {
//        	out.write("上传失败！");
        	response.sendRedirect("../../../view/uploadfile.jsp?fileup=no");
        }
    }  
    private boolean processUploadField(FileItem item) {  
        try {  
            String allfilename = item.getName(); 
            String fileName = null;
            int ind = allfilename.lastIndexOf("\\");
            if(ind != -1) {
            	fileName = allfilename.substring(ind+1);
            }else {
            	fileName = allfilename;
            }
            fileName=fileName.replaceAll(" ", "");
            fileName=fileName.replaceAll("）", ")");
            fileName=fileName.replaceAll("（", "(");
            System.out.println(fileName);
            boolean flag = false;   
            //用户选择上传文件时  
            if(fileName!=null&&!fileName.equals("")){  
//            	SourceBean sBean = new SourceBean();
            	sourceBean.setSname(fileName);
            	//通用唯一识别码
                fileName = UUID.randomUUID().toString()+"_"+FilenameUtils.getName(fileName); 
                sourceBean.setSuname(fileName);
                //得到扩展名  
                String extension = FilenameUtils.getExtension(fileName);  
                //会自动判断下载文件类型  
                String contentType = item.getContentType();  
                //按照文件名的hashCode计算存储目录  
                String childDirectory = makeChildDirectory(getServletContext().getRealPath("/WEB-INF/files/"),fileName);                 
                String storeDirectoryPath = getServletContext().getRealPath("/WEB-INF/files/"+childDirectory); 
                System.out.println(storeDirectoryPath);
                File storeDirectory = new File(storeDirectoryPath);  
                if(!storeDirectory.exists()){  
                    storeDirectory.mkdirs();  
                }  
                flag = true;
                // write方法用于将FileItem对象中保存的主体内容保存到某个指定的文件中。
                //如果FileItem对象中的主体内容是保存在某个临时文件中，该方法顺利完成后，临时文件有可能会被清除。
                item.write(new File(storeDirectoryPath+File.separator+fileName));  //File.separator与系统有关的默认名称分隔符
            }else {
            	 flag = false;
            }  
            return flag;
        } catch (Exception e) {  
            throw new RuntimeException("上传失败,请重试");  
        }  
    }  
    //计算存放的子目录  
    private String makeChildDirectory(String realPath, String fileName) {  
        int hashCode = fileName.hashCode();
        int dir1 = hashCode&0xf;// 取1~4位  
        int dir2 = (hashCode&0xf0)>>4;//取5~8位  
        String directory = ""+dir1+File.separator+dir2;  
        File file = new File(realPath,directory);  
        if(!file.exists())  
            file.mkdirs();  
        return directory;  
    }  
    private Map<String, String> processFormField(FileItem item) { 
    	Map<String , String> map =  new HashMap<String, String>();
        String fieldName = item.getFieldName();//字段名  
        String fieldValue;  
        try {  
            fieldValue = item.getString("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("不支持UTF-8编码");  
        }  
        map.put(fieldName,fieldValue);
        return map;      		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
