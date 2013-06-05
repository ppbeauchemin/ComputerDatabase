package com.excilys.formation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.om.Company;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/new")
public class NewController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request) {
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
		return "new";

	}

}
