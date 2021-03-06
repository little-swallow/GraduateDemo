package com.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.SourceBean;
import com.demo.dao.SourceDao;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String fileid = request.getParameter("fileid");
		int sid = Integer.parseInt(fileid);
		int count = 0;
		SourceBean sourceBean = new SourceBean();
		SourceDao sourceDao = new SourceDao();
		try {
			sourceBean = sourceDao.singleinfo(sid);
			count = sourceBean.getViewcount()+1;
			boolean flag = false;
			flag = sourceDao.updateview(sid, count);
			if(flag) {
//				System.out.println(count);
				sourceBean.setViewcount(count);
			}else {
				System.out.println("wrong");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("Singleinfo", sourceBean);
		session.setAttribute("Sourceid", sid);
		response.sendRedirect("SelectcomServlet");	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
