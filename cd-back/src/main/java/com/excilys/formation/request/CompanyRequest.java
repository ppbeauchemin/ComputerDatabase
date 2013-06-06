package com.excilys.formation.request;

public interface CompanyRequest {
	String GET_COMPANY_BY_ID = "select * from company where id = ?";
	String FIND_ALL_COMPANIES = "select * from company order by company.name";
	String SAVE_COMPANY = "insert into company values(?, ?)";
	String UPDATE_COMPANY = "update company set name = ? where id = ?";
	String DELETE_COMPANY_BY_ID = "delete from company where id = ?";
}