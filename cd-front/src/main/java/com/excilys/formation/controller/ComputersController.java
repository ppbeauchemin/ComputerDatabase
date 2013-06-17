package com.excilys.formation.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.controllerUtils.CompanyConverter;
import com.excilys.formation.controllerUtils.DateConverter;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.Errors;
import com.excilys.formation.utils.ParamConverter;
import com.excilys.formation.utils.Params;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/computers")
public class ComputersController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;

	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConverter());
	}

	@InitBinder
	public void companyBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Company.class, new CompanyConverter(
				computerDatabaseService));
	}

	@RequestMapping(method = RequestMethod.POST)
	public String post(@Valid Computer c, BindingResult result,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String mode = (String) session.getAttribute("mode");
		String name = request.getParameter("name");

		if (result.hasErrors()) {
			if (mode.equals(Var.CREATE)) {
				return "redirect:new.htm";
			} else if (mode.equals(Var.UPDATE)) {
				return "redirect:update.htm?computerId="
						+ session.getAttribute("computerId");
			}
		}

		if (mode.equals(Var.CREATE)) {
			computerDatabaseService.saveComputer(c);
		} else if (mode.equals(Var.UPDATE)) {
			long computerId = (long) session.getAttribute("computerId");
			c.setComputerId(computerId);
			computerDatabaseService.updateComputer(c);
		}
		session.setAttribute("newComputer", name);

		return "redirect:computers.htm";

	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("newComputer") != null) {
			request.setAttribute("newComputer",
					session.getAttribute("newComputer"));
			session.setAttribute("newComputer", null);
		}

		session.setAttribute("errors", new Errors());

		Params params = new Params();
		Integer p = ParamConverter.getIntParameterOrDefault(
				request.getParameter("p"), 0);
		String s = ParamConverter.getStringParameterOrDefault(
				request.getParameter("s"), "name");
		String o = ParamConverter.getStringParameterOrDefault(
				request.getParameter("o"), "asc");
		String f = ParamConverter.getStringParameterOrDefault(
				request.getParameter("f"), "");

		int pLimit = p * Var.MAXCOMPUTER;

		ParamConverter.setAllParams(params, p, s, o, f);

		@SuppressWarnings("unchecked")
		List<Computer> computers = (List<Computer>) request
				.getAttribute("computers");
		if (computers == null || computers.isEmpty()) {
			computers = new ArrayList<Computer>();
		}

		if (!f.isEmpty()) {
			computers = computerDatabaseService.getComputersByName(f, s, o,
					pLimit, Var.MAXCOMPUTER);
		} else {
			computers = computerDatabaseService.findAllComputersLimited(s, o,
					pLimit, Var.MAXCOMPUTER);
		}
		request.setAttribute("computers", computers);
		long nbComputers = computerDatabaseService.countComputers(f);
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("maxcomputer", pLimit + Var.MAXCOMPUTER);
		request.setAttribute("indexcomputer", pLimit + 1);
		params.concatPrevUrl("\"");
		params.concatNextUrl("\"");
		if (p == 0) {
			params.setPrevDisabled(Var.DISABLED);
			params.setPrevUrl("");
		}
		if (pLimit + Var.MAXCOMPUTER >= nbComputers) {
			params.setNextDisabled(Var.DISABLED);
			params.setNextUrl("");
			request.setAttribute("maxcomputer", nbComputers);
		}
		request.setAttribute("params", params);

		return "computers";
	}

	public ComputerDatabaseService getComputerDatabaseService() {
		return computerDatabaseService;
	}

	public void setComputerDatabaseService(
			ComputerDatabaseService computerDatabaseService) {
		this.computerDatabaseService = computerDatabaseService;
	}

}
