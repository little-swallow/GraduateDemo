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
            throw new RuntimeException("�������ı���enctype���ԣ�ȷ����multipart/form-data");  
        }  
        DiskFileItemFactory diskfile = new DiskFileItemFactory();  
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskfile);   
        servletFileUpload.setFileSizeMax(500*1024*1024);		//���õ����ļ��ϴ��Ĵ�С  
        servletFileUpload.setSizeMax(1024*1024*1024);			//���ļ��ϴ�ʱ�ܴ�С����  
        List<FileItem> items = null;  
        try {  
            items = servletFileUpload.parseRequest(request);  
        }catch(FileUploadBase.FileSizeLimitExceededException e) {  
            out.write("�ϴ����ļ�������500M");  
            return;  
        }catch(FileUploadBase.SizeLimitExceededException e){  
            out.write("���ļ�������1G");  
            return;  
        }catch (FileUploadException e) {  
            e.printStackTrace();  
            throw new RuntimeException("�ϴ����ݽ���ʧ�ܣ���������һ��");  
        }  
        //������������  
        if(items!=null){  
            for(FileItem item:items){  
                if(item.isFormField()){
                	Map<String,String> map = new HashMap<String,String>();
                	String fieldname = null;
                	String fieldvalue = null;
                    map = processFormField(item);						//���ı��� 
                    for(Entry<String,String> entry : map.entrySet()) {
                    	fieldname = entry.getKey();
                    	fieldvalue = entry.getValue();
                    }
                    formmap.put(fieldname, fieldvalue);
                }else{  
                    tag = processUploadField(item);  			//���ļ���
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
//            	out.write("�ϴ��ɹ���");
            	response.sendRedirect("../../../view/uploadfile.jsp?fileup=yes");
            }else {
            	out.write("�ϴ����ݿ�ʧ�ܣ�");
			}  
        }else {
//        	out.write("�ϴ�ʧ�ܣ�");
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
            fileName=fileName.replaceAll("��", ")");
            fileName=fileName.replaceAll("��", "(");
            System.out.println(fileName);
            boolean flag = false;   
            //�û�ѡ���ϴ��ļ�ʱ  
            if(fileName!=null&&!fileName.equals("")){  
//            	SourceBean sBean = new SourceBean();
            	sourceBean.setSname(fileName);
            	//ͨ��Ψһʶ����
                fileName = UUID.randomUUID().toString()+"_"+FilenameUtils.getName(fileName); 
                sourceBean.setSuname(fileName);
                //�õ���չ��  
                String extension = FilenameUtils.getExtension(fileName);  
                //���Զ��ж������ļ�����  
                String contentType = item.getContentType();  
                //�����ļ�����hashCode����洢Ŀ¼  
                String childDirectory = makeChildDirectory(getServletContext().getRealPath("/WEB-INF/files/"),fileName);                 
                String storeDirectoryPath = getServletContext().getRealPath("/WEB-INF/files/"+childDirectory); 
                System.out.println(storeDirectoryPath);
                File storeDirectory = new File(storeDirectoryPath);  
                if(!storeDirectory.exists()){  
                    storeDirectory.mkdirs();  
                }  
                flag = true;
                // write�������ڽ�FileItem�����б�����������ݱ��浽ĳ��ָ�����ļ��С�
                //���FileItem�����е����������Ǳ�����ĳ����ʱ�ļ��У��÷���˳����ɺ���ʱ�ļ��п��ܻᱻ�����
                item.write(new File(storeDirectoryPath+File.separator+fileName));  //File.separator��ϵͳ�йص�Ĭ�����Ʒָ���
            }else {
            	 flag = false;
            }  
            return flag;
        } catch (Exception e) {  
            throw new RuntimeException("�ϴ�ʧ��,������");  
        }  
    }  
    //�����ŵ���Ŀ¼  
    private String makeChildDirectory(String realPath, String fileName) {  
        int hashCode = fileName.hashCode();
        int dir1 = hashCode&0xf;// ȡ1~4λ  
        int dir2 = (hashCode&0xf0)>>4;//ȡ5~8λ  
        String directory = ""+dir1+File.separator+dir2;  
        File file = new File(realPath,directory);  
        if(!file.exists())  
            file.mkdirs();  
        return directory;  
    }  
    private Map<String, String> processFormField(FileItem item) { 
    	Map<String , String> map =  new HashMap<String, String>();
        String fieldName = item.getFieldName();//�ֶ���  
        String fieldValue;  
        try {  
            fieldValue = item.getString("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("��֧��UTF-8����");  
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
