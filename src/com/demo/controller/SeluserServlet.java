package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.UserBean;
import com.demo.dao.UserDao;

/**
 * Servlet implementation class SeluserServlet
 */
@WebServlet("/SeluserServlet")
public class SeluserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeluserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("Adminname");
		if(name == null) {
//			response.sendRedirect("../../../view/admin/adlogin.jsp");
			PrintWriter out = response.getWriter();  
		    out.println("<html>");      
		    out.println("<script>");      
		    out.println("window.open ('"+request.getContextPath()+"/view/admin/adlogin.jsp','_top')");      
		    out.println("</script>");      
		    out.println("</html>");    
		}
		else {
			UserDao userDao = new UserDao();
			ArrayList<UserBean> userBeans = new ArrayList<UserBean>();
			try {
				userBeans = userDao.selectalluser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("alluser", userBeans); 
			response.sendRedirect("../../../view/admin/muser.jsp"); 
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
