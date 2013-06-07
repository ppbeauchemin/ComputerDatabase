package com.excilys.formation.controller;

import java.sql.Date;
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
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.utils.DateConverter;
import com.excilys.formation.utils.Errors;
import com.excilys.formation.utils.Params;
import com.excilys.formation.utils.Var;

@Controller
@RequestMapping("/computers")
public class ComputersController {
	@Autowired
	private ComputerDatabaseService computerDatabaseService;

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

		int p = 0;
		if (request.getParameter("p") != null) {
			p = Integer.parseInt(request.getParameter("p"));
		}
		String s = "computer.name";
		if (request.getParameter("s") != null) {
			s = request.getParameter("s");
		}
		String o = "asc";
		if (request.getParameter("o") != null) {
			o = request.getParameter("o");
		}
		String f = "";
		if (request.getParameter("f") != null) {
			f = request.getParameter("f");
		}

		int pPrev = p - 1;
		int pNext = p + 1;
		int pLimit = p * Var.MAXCOMPUTER;

		params.concatPrevUrl("p=" + pPrev);
		params.concatNextUrl("p=" + pNext);

		if (o.equals("asc")) {
			if (s.equals("computer.name")) {
				params.concatName("?o=desc");
				params.setNameHeader("headerSortUp");
			} else if (s.equals("introduced")) {
				params.concatIntroduced("&o=desc");
				params.setIntroducedHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			} else if (s.equals("discontinued")) {
				params.concatDiscontinued("&o=desc");
				params.setDiscontinuedHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			} else if (s.equals("company.name")) {
				params.concatCompanyName("&o=desc");
				params.setCompanyNameHeader("headerSortUp");
				params.concatPrevUrl("&s=" + s);
				params.concatNextUrl("&s=" + s);
			}
		} else if (o.equals("desc")) {
			params.concatPrevUrl("&o=" + o);
			params.concatNextUrl("&o=" + o);
			if (s.equals("computer.name")) {
				params.setNameHeader("headerSortDown");
			} else if (s.equals("introduced")) {
				params.setIntroducedHeader("headerSortDown");
			} else if (s.equals("discontinued")) {
				params.setDiscontinuedHeader("headerSortDown");
			} else if (s.equals("company.name")) {
				params.setCompanyNameHeader("headerSortDown");
			}
		}

		if (!f.isEmpty()) {
			if (o.equals("asc") && s.equals("computer.name")) {
				params.concatName("&f=" + f);
			} else {
				params.concatName("?f=" + f);
			}
			params.concatIntroduced("&f=" + f);
			params.concatDiscontinued("&f=" + f);
			params.concatCompanyName("&f=" + f);
			params.concatPrevUrl("&f=" + f);
			params.concatNextUrl("&f=" + f);
		}

		@SuppressWarnings("unchecked")
		List<Computer> computers = (List<Computer>) request
				.getAttribute("computers");
		if (computers == null || computers.isEmpty()) {
			computers = new ArrayList<Computer>();
		}
		try {
			if (!f.isEmpty()) {
				computers = computerDatabaseService.getComputersByName(f, s, o,
						pLimit, Var.MAXCOMPUTER);
			} else {
				computers = computerDatabaseService.findAllComputersLimited(s,
						o, pLimit, Var.MAXCOMPUTER);
			}
			request.setAttribute("computers", computers);
			int nbComputers = computerDatabaseService.countComputers(f);
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "computers";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Errors errors = new Errors();
		String mode = (String) session.getAttribute("mode");
		String name = request.getParameter("name");
		String sIntroduced = request.getParameter("introduced");
		String sDiscontinued = request.getParameter("discontinued");
		Date introduced = null;
		Date discontinued = null;

		if (name == null || name.isEmpty()) {
			errors.setName(Var.ERROR);
		}

		Object introducedReturn = DateConverter.convert(sIntroduced);
		if (introducedReturn != null && introducedReturn.equals(Var.ERROR)) {
			errors.setIntroduced(Var.ERROR);
		} else {
			introduced = (Date) introducedReturn;
		}

		Object discontinuedReturn = DateConverter.convert(sDiscontinued);
		if (discontinuedReturn != null && discontinuedReturn.equals(Var.ERROR)) {
			errors.setDiscontinued(Var.ERROR);
		} else {
			discontinued = (Date) discontinuedReturn;
		}

		Long company_id = null;
		if (request.getParameter("company.id") != null
				&& !request.getParameter("company.id").isEmpty()) {
			company_id = Long.parseLong(request.getParameter("company.id"));
		}

		session.setAttribute("errors", errors);

		if (errors.isError()) {
			if (mode.equals(Var.CREATE)) {
				return "redirect:new.htm";
			} else if (mode.equals(Var.UPDATE)) {
				return "redirect:update.htm?computerId="
						+ session.getAttribute("computerId");
			}
		} else {
			Computer computer = new Computer();
			try {
				Company company = null;
				if (company_id != null) {
					company = computerDatabaseService
							.getCompanyById(company_id);
				}
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany(company);
				if (mode.equals(Var.CREATE)) {
					computerDatabaseService.saveComputer(computer);
				} else if (mode.equals(Var.UPDATE)) {
					long computerId = (long) session.getAttribute("computerId");
					computer.setComputerId(computerId);
					computerDatabaseService.updateComputer(computer);
				}
				session.setAttribute("newComputer", name);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return "redirect:computers.htm";
	}

}
