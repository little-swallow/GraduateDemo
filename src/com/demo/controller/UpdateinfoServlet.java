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
 * Servlet implementation class UpdateinfoServlet
 */
@WebServlet("/UpdateinfoServlet")
public class UpdateinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateinfoServlet() {
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
		int cid = (int) session.getAttribute("Userid");
		String updatename = request.getParameter("nameinfo");
		String updateemail = request.getParameter("emailinfo");
		String updatephone = request.getParameter("phoneinfo");
		
		UserBean userBean = new UserBean();
		userBean.setCid(cid);
		userBean.setName(updatename);
		userBean.setEmail(updateemail);
		userBean.setPhone(updatephone);
		session.setAttribute("Userinfo", userBean);
		
		UserDao userDao = new UserDao();
		boolean flag = false;
		
		try {
			flag = userDao.updateuserinfo(userBean);
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
