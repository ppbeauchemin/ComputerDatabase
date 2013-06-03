package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.service.impl.ComputerDatabaseServiceImpl;
import com.excilys.formation.utils.Var;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDatabaseService computerDatabaseService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		computerDatabaseService = new ComputerDatabaseServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long computerId = Long.parseLong(request.getParameter("computerId"));
		Computer computer = new Computer();
		try {
			computer = computerDatabaseService.getComputerById(computerId);
			session.setAttribute("companies",
					computerDatabaseService.findAllCompanies());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("computer", computer);
		session.setAttribute("computerId", computerId);
		session.setAttribute("mode", Var.UPDATED);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp")
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
