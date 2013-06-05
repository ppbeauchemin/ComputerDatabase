package com.excilys.formation.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.dao.impl.CompanyDaoImpl;
import com.excilys.formation.dao.impl.ComputerDaoImpl;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;

@Service
public class ComputerDatabaseServiceImpl implements ComputerDatabaseService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;

	public ComputerDatabaseServiceImpl() {
		companyDao = new CompanyDaoImpl();
		computerDao = new ComputerDaoImpl();
	}

	@Override
	public Computer getComputerById(long computerId)
			throws ClassNotFoundException, SQLException {
		return computerDao.getComputerById(computerId);
	}

	@Override
	public List<Computer> getComputersByName(String name, String s, String o, int p, int n)
			throws ClassNotFoundException, SQLException {
		return computerDao.getComputersByName(name, s, o, p, n);
	}

	@Override
	public List<Computer> findAllComputers() throws ClassNotFoundException,
			SQLException {
		return computerDao.findAllComputers();
	}

	@Override
	public List<Computer> findAllComputersLimited(String s, String o, int p,
			int n) throws ClassNotFoundException, SQLException {
		return computerDao.findAllComputersLimited(s, o, p, n);
	}

	@Override
	public int countComputers(String name) throws ClassNotFoundException, SQLException {
		return computerDao.countComputers(name);
	}

	@Override
	public void saveComputer(Computer computer) throws ClassNotFoundException,
			SQLException {
		computerDao.saveComputer(computer);
	}

	@Override
	public void updateComputer(Computer computer)
			throws ClassNotFoundException, SQLException {
		computerDao.updateComputer(computer);
	}

	@Override
	public void deleteComputerById(long computerId)
			throws ClassNotFoundException, SQLException {
		computerDao.deleteComputerById(computerId);
	}

	@Override
	public Company getCompanyById(long companyId)
			throws ClassNotFoundException, SQLException {
		return companyDao.getCompanyById(companyId);
	}

	@Override
	public List<Company> findAllCompanies() throws ClassNotFoundException,
			SQLException {
		return companyDao.findAllCompanies();
	}

	@Override
	public void saveCompany(Company company) throws ClassNotFoundException,
			SQLException {
		companyDao.saveCompany(company);
	}

	@Override
	public void updateCompany(Company company) throws ClassNotFoundException,
			SQLException {
		companyDao.updateCompany(company);
	}

	@Override
	public void deleteCompanyById(long companyId)
			throws ClassNotFoundException, SQLException {
		companyDao.deleteCompanyById(companyId);
	}

	@Override
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	@Override
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public ComputerDao getComputerDao() {
		return computerDao;
	}

	@Override
	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

}
