package com.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.demo.bean.UserBean;
import com.demo.dao.UserDao;

/**
 * Servlet implementation class SelfinfoServlet
 */
@WebServlet("/SelfinfoServlet")
public class SelfinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelfinfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cid = (String)session.getAttribute("Suserid");
		if(cid == null) { 
			System.out.println("Ã»µÇÂ¼");
			response.sendRedirect("../../../view/home.jsp?login=no");
		}else {
			int id =  (int)session.getAttribute("Userid");
			UserDao userDao = new UserDao();
			UserBean user =new UserBean();
			try {
				user = userDao.selectuserinfo(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("../../../view/selfinfo.jsp");	
			session.setAttribute("Userinfo", user);	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
