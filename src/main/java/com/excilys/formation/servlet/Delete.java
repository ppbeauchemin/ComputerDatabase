package com.excilys.formation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.service.impl.ComputerDatabaseServiceImpl;
import com.excilys.formation.utils.Var;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ComputerDatabaseService computerDatabaseService = new ComputerDatabaseServiceImpl();
		HttpSession session = request.getSession();
		long computerId = Integer.parseInt(request.getParameter("computerId"));
		try {
			computerDatabaseService.deleteComputerById(computerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("mode", Var.DELETED);
		session.setAttribute("newComputer", "");
		response.sendRedirect("computers");
	}

}
