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
 * Servlet implementation class ChecknameServlet
 */
@WebServlet("/ChecknameServlet")
public class ChecknameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChecknameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8"); 
		String name = request.getParameter("username");
		name = name.trim();
		HttpSession session = request.getSession();
		String cid = (String)session.getAttribute("Suserid");
		if(cid == null) { 
			UserDao userDao = new UserDao();
			boolean flag = false;
			try {
				flag = userDao.checkname(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag) {
				response.getWriter().write("no"); 
			}else {
				response.getWriter().write("yes"); 
			}
		}else {
			UserBean user = (UserBean) session.getAttribute("Userinfo");
			String oldname = user.getName();
			oldname = oldname.trim();
//			System.out.println(name);
//			System.out.println(oldname);
			if(name.equals(oldname)) {
//				System.out.println("yes");
				response.getWriter().write("same");
			}else {
//				System.out.println("no");
				UserDao userDao = new UserDao();
				boolean flag = false;
				try {
					flag = userDao.checkname(name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(flag) {
					response.getWriter().write("no"); 
				}else {
					response.getWriter().write("yes"); 
				}
			}
		}
	}
}
