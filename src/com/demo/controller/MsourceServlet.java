package com.demo.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.SourceDao;

/**
 * Servlet implementation class MsourceServlet
 */
@WebServlet("/MsourceServlet")
public class MsourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = request.getParameter("oldname");
		System.out.println(filename);
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(filename);   
        String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");  
        //得到存放的子目录  
        String childDirecotry = makeChildDirectory(storeDirectory, filename); 
        String storeDirectoryPath = storeDirectory+File.separator+childDirecotry+File.separator+filename;
        System.out.println(storeDirectoryPath);
        File deletefile = new File(storeDirectoryPath); 
        boolean flag = false;
        if(deletefile.exists()) {
        	flag = deletefile.delete();
        	if(flag) {
        		SourceDao sourceDao = new SourceDao();
        		boolean tag = false;
        		try {
					tag = sourceDao.deletefile(filename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		if(tag) {
        			System.out.println("成功");
            		response.sendRedirect("SelsouServlet");
        		}else {
            		System.out.println("数据库操作失败");
        		}
        	}else {
        		System.out.println("失败");
        	}
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
