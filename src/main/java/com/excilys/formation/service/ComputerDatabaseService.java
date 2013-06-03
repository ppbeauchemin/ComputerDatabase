package com.excilys.formation.service;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.dao.ComputerDao;

public interface ComputerDatabaseService extends ComputerDao, CompanyDao {
	
	public CompanyDao getCompanyDao();

	public void setCompanyDao(CompanyDao companyDao);

	public ComputerDao getComputerDao();

	public void setComputerDao(ComputerDao computerDao);

}
