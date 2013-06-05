package com.excilys.formation.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/delete")
public class DeleteController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		long computerId = Long.parseLong(request.getParameter("computerId"));
		try {
			computerDatabaseService.deleteComputerById(computerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("mode", Var.DELETED);
		session.setAttribute("newComputer", "");
		return "redirect:computers.htm";
	}

}
