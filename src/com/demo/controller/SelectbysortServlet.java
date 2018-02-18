package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.SourceBean;
import com.demo.dao.SourceDao;

/**
 * Servlet implementation class SelectbysortServlet
 */
@WebServlet("/SelectbysortServlet")
public class SelectbysortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectbysortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sort = request.getParameter("sort");
		sort = new String(sort.getBytes("ISO-8859-1"),"UTF-8");
		HttpSession session = request.getSession();
		SourceDao sourceDao = new SourceDao();
		ArrayList<SourceBean> sourceinfo = new ArrayList<SourceBean>();
		try {
			sourceinfo = sourceDao.selectbysort(sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("info", sourceinfo); 
		response.sendRedirect("../../../view/home.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
