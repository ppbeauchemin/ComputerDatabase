package com.excilys.formation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/update")
public class UpdateController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		long computerId = Long.parseLong(request.getParameter("computerId"));
		Computer computer = new Computer();
		computer = computerDatabaseService.getComputerById(computerId);
		request.setAttribute("companies",
				computerDatabaseService.findAllCompanies());
		modelMap.addAttribute("computer", computer);
		session.setAttribute("computerId", computerId);
		session.setAttribute("mode", Var.UPDATE);
		return "new";
	}

	public ComputerDatabaseService getComputerDatabaseService() {
		return computerDatabaseService;
	}

	public void setComputerDatabaseService(
			ComputerDatabaseService computerDatabaseService) {
		this.computerDatabaseService = computerDatabaseService;
	}

}
