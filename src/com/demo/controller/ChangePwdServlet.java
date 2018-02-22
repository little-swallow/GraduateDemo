package com.demo.controller;

import java.io.Console;
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
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet("/ChangePwdServlet")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
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
		String pwd = request.getParameter("password"); 
		HttpSession session = request.getSession();
		int cid = (int) session.getAttribute("Userid");
		UserBean userBean = new UserBean();
		userBean.setCid(cid);
		userBean.setPwd(pwd);
		UserDao userDao = new UserDao();
		boolean flag = false;
		try {
			flag = userDao.updatepwdinfo(userBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			response.getWriter().write("success"); 
		}else {
			response.getWriter().write("fail"); 
		}
	}

}
