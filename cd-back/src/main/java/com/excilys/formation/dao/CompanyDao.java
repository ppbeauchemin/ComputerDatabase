package com.excilys.formation.dao;

import java.util.List;

import com.excilys.formation.om.Company;

public interface CompanyDao {
	
	public Company getCompanyById(long companyId);

	public List<Company> findAllCompanies();

	public void saveCompany(Company company);

	public void updateCompany(Company company);

	public void deleteCompanyById(long companyId);

}
