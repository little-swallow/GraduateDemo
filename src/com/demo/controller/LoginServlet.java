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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("logname");
		String pwd = request.getParameter("logpass");
		UserBean user = new UserBean();
		user.setName(name);
		user.setPwd(pwd);
		
		// 判断登录是否成功
		UserDao userDao = new UserDao();
		int id = 0 ;
		try {
			id = userDao.login(user);	////userdao的login方法的调用
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id == 0) {
			response.sendRedirect("../../../view/login.jsp?error=yes");	
			//如果返回值为0，表示数据库未查到此数据，表示登录失败，在登录页面的时候带上参数error=yes
		}else {
			session.setAttribute("Userid",id);
			String id1 = Integer.toString(id);
			session.setAttribute("Suserid",id1);
			response.sendRedirect("../../../view/main.jsp");
		}
	}
}
