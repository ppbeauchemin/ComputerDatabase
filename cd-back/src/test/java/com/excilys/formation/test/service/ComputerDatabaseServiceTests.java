package com.excilys.formation.test.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.dao.impl.CompanyDaoImpl;
import com.excilys.formation.dao.impl.ComputerDaoImpl;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;
import com.excilys.formation.service.impl.ComputerDatabaseServiceImpl;

public class ComputerDatabaseServiceTests {
	ComputerDatabaseService computerDatabaseService;

	@Before
	public void setUp() throws Exception {
		computerDatabaseService = new ComputerDatabaseServiceImpl();
		computerDatabaseService.setCompanyDao(new CompanyDaoImpl());
		computerDatabaseService.setComputerDao(new ComputerDaoImpl());
	}

	@After
	public void tearDown() throws Exception {
		computerDatabaseService = null;
	}

	@Test
	public void getCompanyByIdTest() throws ClassNotFoundException,
			SQLException {
		Company companyFinded = computerDatabaseService.getCompanyById(1l);
		assertEquals("Apple Inc.", companyFinded.getName());
	}

	@Test
	public void findAllCompaniesTest() throws ClassNotFoundException,
			SQLException {
		CompanyDao companyDao = new CompanyDaoImpl();
		List<Company> lCompaniesDao = companyDao.findAllCompanies();
		List<Company> lCompaniesService = computerDatabaseService.findAllCompanies();
		assertEquals(lCompaniesDao, lCompaniesService);
	}
	
	@Test
	public void findAllComputersTest() throws ClassNotFoundException,
			SQLException {
		ComputerDao computerDao = new ComputerDaoImpl();
		List<Computer> lComputersDao = computerDao.findAllComputers();
		List<Computer> lComputersService = computerDatabaseService.findAllComputers();
		assertEquals(lComputersDao, lComputersService);
	}
	
}
