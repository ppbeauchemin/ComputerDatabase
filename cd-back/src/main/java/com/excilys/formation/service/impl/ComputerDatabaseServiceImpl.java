package com.excilys.formation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;
import com.excilys.formation.service.ComputerDatabaseService;

@Service
public class ComputerDatabaseServiceImpl implements ComputerDatabaseService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;

	@Override
	public Computer getComputerById(long computerId) {
		return computerDao.getComputerById(computerId);
	}

	@Override
	public List<Computer> getComputersByName(String name, String s, String o,
			int p, int n) {
		return computerDao.getComputersByName(name, s, o, p, n);
	}

	@Override
	public List<Computer> findAllComputers() {
		return computerDao.findAllComputers();
	}

	@Override
	public List<Computer> findAllComputersLimited(String s, String o, int p,
			int n) {
		return computerDao.findAllComputersLimited(s, o, p, n);
	}

	@Override
	public long countComputers(String name) {
		return computerDao.countComputers(name);
	}

	@Override
	public void saveComputer(Computer computer) {
		computerDao.saveComputer(computer);
	}

	@Override
	public void updateComputer(Computer computer) {
		computerDao.updateComputer(computer);
	}

	@Override
	public void deleteComputerById(long computerId) {
		computerDao.deleteComputerById(computerId);
	}

	@Override
	public Company getCompanyById(long companyId) {
		return companyDao.getCompanyById(companyId);
	}

	@Override
	public List<Company> findAllCompanies() {
		return companyDao.findAllCompanies();
	}

	@Override
	public void saveCompany(Company company) {
		companyDao.saveCompany(company);
	}

	@Override
	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
	}

	@Override
	public void deleteCompanyById(long companyId) {
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
