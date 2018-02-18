package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.SourceDao;




/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                                                         
		String filename = request.getParameter("oldname");
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
        String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");  
        //得到存放的子目录  
        String childDirecotry = makeChildDirectory(storeDirectory, filename);  
          
        //构建输入流  
        InputStream in = new FileInputStream(storeDirectory+File.separator+childDirecotry+File.separator+filename);  
        //下载  
        String oldfilename = filename.substring(filename.indexOf("_")+1);  
        //通知客户端以下载的方式打开  
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(oldfilename, "UTF-8"));       
        OutputStream out = response.getOutputStream();  
          
        int len = -1;  
        byte b[] = new byte[1024];  
        while((len=in.read(b))!=-1){  
            out.write(b,0,len);  
        }  
        in.close();  
        out.close();
        
        SourceDao sourceDao = new SourceDao();
		boolean flag = false;
		try {
			flag = sourceDao.updatedown(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			System.out.println("download+1");
		}else {
			System.out.println("update download fail");
		}
	 }
	 private String makeChildDirectory(String realPath, String fileName) {  
	        int hashCode = fileName.hashCode();  
	        int dir1 = hashCode&0xf;// 取1~4位  
	        int dir2 = (hashCode&0xf0)>>4;//取5~8位 
	        String directory = ""+dir1+File.separator+dir2;  
	        File file = new File(realPath,directory);  
	        if(!file.exists())  
	            file.mkdirs();
	        System.out.println(directory);
	        return directory;  
	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
