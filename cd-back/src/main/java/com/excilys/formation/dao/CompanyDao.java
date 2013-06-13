package com.excilys.formation.dao;

import java.util.List;

import com.excilys.formation.om.Company;
import com.excilys.formation.request.CompanyRequest;

public interface CompanyDao extends CompanyRequest {
	
	public Company getCompanyById(long companyId);

	public List<Company> findAllCompanies();

	public void saveCompany(Company company);

	public void updateCompany(Company company);

	public void deleteCompanyById(long companyId);

}
