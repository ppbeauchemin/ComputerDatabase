package com.excilys.formation.test.model;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;

public class ComputerTests {
	Computer pc;

	@Before
	public void setUp() throws Exception {
		pc = new Computer();
	}

	@After
	public void tearDown() throws Exception {
		pc = null;
	}

	@Test
	public void computerIdTest() {
		pc.setComputerId(1);
		assertEquals(1, pc.getComputerId());
	}
	
	@Test
	public void nameTest() {
		pc.setName("Asus");
		assertEquals("Asus", pc.getName());
	}
	
	@Test
	public void introducedTest() {
		Date d = new Date(5468465165454L);
		pc.setIntroduced(d);
		assertEquals(d, pc.getIntroduced());
	}
	
	@Test
	public void discontinuedTest() {
		Date d = new Date(5468465165454L);
		pc.setDiscontinued(d);
		assertEquals(d, pc.getDiscontinued());
	}
	
	@Test
	public void companyIdTest() {
		Company company = new Company();
		pc.setCompany(company);
		assertEquals(company, pc.getCompany());
	}
}
