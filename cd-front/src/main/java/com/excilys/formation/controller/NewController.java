package com.excilys.formation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/new")
public class NewController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		modelMap.addAttribute("computer", new Computer());
		List<Company> companies = new ArrayList<Company>();
		try {
			companies = computerDatabaseService.findAllCompanies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("companies", companies);
		session.setAttribute("mode", Var.CREATE);
		return "new";
	}

}
