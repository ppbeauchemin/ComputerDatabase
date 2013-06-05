package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.formation.om.Company;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.service.impl.ComputerDatabaseServiceImpl;
import com.excilys.formation.utils.Var;

/**
 * Servlet implementation class New
 */
@WebServlet("/new")
public class New extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDatabaseService computerDatabaseService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public New() {
		computerDatabaseService = new ComputerDatabaseServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Company> companies = (List<Company>) session
				.getAttribute("companies");
		if (companies == null || companies.isEmpty()) {
			companies = new ArrayList<Company>();
		}
		try {
			companies = computerDatabaseService.findAllCompanies();
			session.setAttribute("companies", companies);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("mode", Var.CREATED);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/new.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
