package com.excilys.formation.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.om.Company;
import com.excilys.formation.request.CompanyRequest;

public interface CompanyDao extends CompanyRequest {
	
	public Company getCompanyById(long companyId) throws ClassNotFoundException, SQLException;

	public List<Company> findAllCompanies() throws ClassNotFoundException, SQLException;

	public void saveCompany(Company company) throws ClassNotFoundException, SQLException;

	public void updateCompany(Company company) throws ClassNotFoundException, SQLException;

	public void deleteCompanyById(long companyId) throws ClassNotFoundException, SQLException;

}
