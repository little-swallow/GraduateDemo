package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.CommentBean;
import com.demo.dao.CommentDao;

/**
 * Servlet implementation class DelcomServlet
 */
@WebServlet("/DelcomServlet")
public class DelcomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelcomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int uid = (int)session.getAttribute("Userid");
		int sid = (int)session.getAttribute("Sourceid");
		CommentDao commentDao = new CommentDao();
		ArrayList<CommentBean> commentBeans = new ArrayList<CommentBean>();
		boolean flag = false;
		try {
			flag = commentDao.deletecom(uid, sid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			try {
				commentBeans = commentDao.selectbysid(sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("commentinfo", commentBeans); 
			response.sendRedirect("../../../view/sourcecontent.jsp");
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
