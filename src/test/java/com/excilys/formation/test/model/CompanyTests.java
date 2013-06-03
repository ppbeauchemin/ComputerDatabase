package com.excilys.formation.test.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.om.Company;

public class CompanyTests {
	Company co;

	@Before
	public void setUp() throws Exception {
		co = new Company();
	}

	@After
	public void tearDown() throws Exception {
		co = null;
	}

	@Test
	public void companyIdTest() {
		co.setCompanyId(1);
		assertEquals(1, co.getCompanyId());
	}

	@Test
	public void nameTest() {
		co.setName("Excilys");
		assertEquals("Excilys", co.getName());
	}

}
