package com.excilys.formation.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.dao.impl.ComputerDaoImpl;
import com.excilys.formation.om.Computer;

public class ComputerDaoTests {
	final static Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
	ComputerDao computerDao;
	Computer computer;

	@Before
	public void setUp() throws Exception {
		computerDao = new ComputerDaoImpl();
		computer = new Computer();
		computer.setComputerId(9999999999l);
		computer.setName("pp");
		computer.setIntroduced(timestamp);
		computer.setDiscontinued(timestamp);
		computer.setCompany(null);
		computerDao.saveComputer(computer);
	}

	@After
	public void tearDown() throws Exception {
		computerDao.deleteComputerById(9999999999l);
		computer = null;
		computerDao = null;
	}

	@Test
	public void getComputerByIdTest() throws ClassNotFoundException,
			SQLException {
		Computer computerFinded = computerDao.getComputerById(9999999999l);
		assertEquals(computer, computerFinded);
	}
	
	@Test
	public void getComputersByName() throws ClassNotFoundException,
			SQLException {
		List<Computer> lComputers = computerDao.getComputersByName("p", "computer.name", "asc", 0, 10000);
		assertTrue(lComputers.contains(computer));
	}
	
	@Test
	public void findAllComputersTest() throws ClassNotFoundException,
			SQLException {
		List<Computer> lComputers = computerDao.findAllComputers();
		assertTrue(lComputers.contains(computer));
	}

	@Test
	public void updateComputerTest() throws ClassNotFoundException, SQLException {
		computer.setName("momo");
		computerDao.updateComputer(computer);
		Computer computerUpdated = computerDao.getComputerById(9999999999l);
		assertEquals(computer, computerUpdated);
	}

	@Test
	public void saveAndDeleteComputerTest() throws ClassNotFoundException,
			SQLException {
		Computer computerSaved = new Computer();
		computerSaved.setComputerId(19999999999l);
		computerSaved.setName("ouais");
		computerSaved.setIntroduced(timestamp);
		computerSaved.setDiscontinued(timestamp);
		computerSaved.setCompany(null);
		computerDao.saveComputer(computerSaved);
		Computer computerFinded = computerDao.getComputerById(19999999999l);
		assertEquals(computerSaved, computerFinded);
		computerDao.deleteComputerById(19999999999l);
		assertNull(computerDao.getComputerById(19999999999l));
	}

}
