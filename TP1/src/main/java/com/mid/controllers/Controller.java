package com.mid.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mid.dao.LivreDao;
import com.mid.dao.UserDao;
import com.mid.model.Livre;

/**
 * Servlet implementation class ControllerLivre
 */
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDAO;
	private LivreDao livreDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		userDAO = new UserDao();
		livreDAO = new LivreDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/listlivre":
			listlivre(request, response);
			break;
		case "/ADMIN/insertlivre":
			insertlivre(request, response);
			break;
		case "/ADMIN/updatelivre":
			updatelivre(request, response);
			break;
		case "/ADMIN/editlivre":
			showeditLivre(request, response);
			break;
		case "/ADMIN/deletelivre":
			deletelivre(request, response);
			break;
		case "/selectlivre":
			showeditLivre(request, response);
			break;
		case "/ADMIN/logout":
			logout(request, response);
			break;
		case "/logout":
			logout(request, response);
			
		}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

		
	
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession();
	session.removeAttribute("login");
	session.removeAttribute("password");
	session.removeAttribute("role");
	String referrer = request.getHeader("referer");
	response.sendRedirect(referrer);
	
	}


	private void deletelivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		livreDAO.deleteLivre(isbn);
		response.sendRedirect("http://localhost:8080/TP1/ADMIN/Livre.jsp");
		
	}



	private void insertlivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int isbn = Integer.parseInt(request.getParameter("isbn")) ;		
		String titre = request.getParameter("titre");
		String editeur = request.getParameter("editeur");
		String[] dd = request.getParameter("date_edition").split("-");
		LocalDate d = LocalDate.of(Integer.parseInt(dd[0]), Integer.parseInt(dd[1]), Integer.parseInt(dd[2]));
		ZonedDateTime zdt = d.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		int matricule = Integer.parseInt(request.getParameter("matricule"));
		String desc = request.getParameter("description");
		Livre livre = new Livre(isbn, titre, editeur,desc, date, matricule);
		livreDAO.insertLivre(livre);
		System.out.print(desc);
		Livre existingbook = livreDAO.selectLivre(isbn);
		 if(existingbook!=null) {
			 request.setAttribute("livreexiste", existingbook);
		 }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ADMIN/Livre.jsp");
		dispatcher.forward(request, response);
	}


	private void listlivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Livre> listlivres = livreDAO.selectAllLivres();
		request.setAttribute("listlivres", listlivres);
		RequestDispatcher dispatcher = request.getRequestDispatcher("booklistcategorie.jsp");
		dispatcher.forward(request, response);
	}

	
	private void showeditLivre(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		Livre existingBook = livreDAO.selectLivre(isbn);
		request.setAttribute("Livre", existingBook);
		String referrer = request.getHeader("referer");
		if(referrer.equals("http://localhost:8080/TP1/ADMIN/Livre.jsp")) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ADMIN/Livreupdate.jsp");
		dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/infosbook.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	private void updatelivre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int isbn = Integer.parseInt(request.getParameter("isbn")) ;		
		String titre = request.getParameter("titre");
		String editeur = request.getParameter("editeur");
		String[] dd = request.getParameter("date_edition").split("-");
		LocalDate d = LocalDate.of(Integer.parseInt(dd[0]), Integer.parseInt(dd[1]), Integer.parseInt(dd[2]));
		ZonedDateTime zdt = d.atStartOfDay(ZoneId.systemDefault());
		Instant instant = zdt.toInstant();
		Date date = Date.from(instant);
		int matricule = Integer.parseInt(request.getParameter("matricule"));
		String desc = request.getParameter("description");
		Livre livre = new Livre(isbn, titre, editeur,desc, date, matricule);

		livreDAO.updateLivre(livre);
		response.sendRedirect("http://localhost:8080/TP1/ADMIN/Livre.jsp");
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
