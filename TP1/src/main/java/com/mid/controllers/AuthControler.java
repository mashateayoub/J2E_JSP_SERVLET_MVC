package com.mid.controllers;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mid.dao.UserDao;
import com.mid.model.User;

/**
 * Servlet implementation class AuthControler
 */
@WebServlet("/auth")
public class AuthControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		userDAO = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String login=request.getParameter("email");
		 String pass=request.getParameter("password");
		 User user=userDAO.selectUser(login,pass);
		 
		 if(user==null ){ 
			 request.setAttribute("isLogged", false);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
	    }
		 else {
			 HttpSession session = request.getSession();
			 session.setAttribute("login", user.getLogin());
			 session.setAttribute("password", user.getPassword());
			 session.setAttribute("role", user.getRole());
			 session.setAttribute("isLogged", true);
			 
			 if(user.getRole().equals("admin")) {
				 response.sendRedirect("/TP1/ADMIN/admindashboard.jsp");
					
			 }else {
				 response.sendRedirect("/TP1/userdashboard.jsp");
			 }
			 
		 }
	}

}
