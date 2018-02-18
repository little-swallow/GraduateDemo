package com.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.AdminBean;
import com.demo.dao.AdminDao;

/**
 * Servlet implementation class AdloginServlet
 */
@WebServlet("/AdloginServlet")
public class AdloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdloginServlet() {
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
		AdminBean adminBean = new AdminBean();
		adminBean.setAdname(name);
		adminBean.setAdpass(pwd);
		boolean flag = false;
		AdminDao adminDao = new AdminDao();
		try {
			flag = adminDao.login(adminBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			session.setAttribute("Adminname",name);
			response.sendRedirect("../../../view/admin/admain.jsp");
		}else {
			response.sendRedirect("../../../view/admin/adlogin.jsp?error=yes");
			
		}
	}
}
