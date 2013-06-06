package com.excilys.formation.test.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.dao.impl.CompanyDaoImpl;
import com.excilys.formation.om.Company;

public class CompanyDaoTests {
	CompanyDao companyDao;
	Company company;

	@Before
	public void setUp() throws Exception {
		companyDao = new CompanyDaoImpl();
		company = new Company();
		company.setCompanyId(9999999999l);
		company.setName("pp");
		companyDao.saveCompany(company);
	}

	@After
	public void tearDown() throws Exception {
		companyDao.deleteCompanyById(9999999999l);
		company = null;
		companyDao = null;
	}

	@Test
	public void getCompanyByIdTest() throws ClassNotFoundException,
			SQLException {
		Company companyFinded = companyDao.getCompanyById(9999999999l);
		assertEquals(company, companyFinded);
	}

	@Test
	public void findAllCompaniesTest() throws ClassNotFoundException,
			SQLException {
		List<Company> lCompanies = companyDao.findAllCompanies();
		assertTrue(lCompanies.contains(company));
	}

	@Test
	public void updateCompanyTest() throws ClassNotFoundException, SQLException {
		company.setName("momo");
		companyDao.updateCompany(company);
		Company companyUpdated = companyDao.getCompanyById(9999999999l);
		assertEquals(company, companyUpdated);
	}

	@Test
	public void saveAndDeleteCompanyTest() throws ClassNotFoundException,
			SQLException {
		Company companySaved = new Company();
		companySaved.setCompanyId(19999999999l);
		companySaved.setName("ouais");
		companyDao.saveCompany(companySaved);
		Company companyFinded = companyDao.getCompanyById(19999999999l);
		assertEquals(companySaved, companyFinded);
		companyDao.deleteCompanyById(19999999999l);
		assertNull(companyDao.getCompanyById(19999999999l));
	}

}
