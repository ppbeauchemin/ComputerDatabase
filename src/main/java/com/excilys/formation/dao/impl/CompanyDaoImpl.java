package com.excilys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.conn.Conn;
import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.om.Company;

public class CompanyDaoImpl implements CompanyDao {
	private Connection conn;

	@Override
	public Company getCompanyById(long companyId)
			throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(GET_COMPANY_BY_ID);
		ps.setLong(1, companyId);
		ResultSet rs = ps.executeQuery();
		Company company = null;
		if (rs.first()) {
			company = new Company();
			company.setCompanyId(rs.getLong("id"));
			company.setName(rs.getString("name"));
		}
		conn.close();
		return company;
	}

	@Override
	public List<Company> findAllCompanies() throws SQLException,
			ClassNotFoundException {
		conn = Conn.getConnection();
		List<Company> lCompanies = new ArrayList<Company>();
		Company company = null;
		PreparedStatement ps = conn.prepareStatement(FIND_ALL_COMPANIES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			company = new Company();
			company.setCompanyId(rs.getLong("id"));
			company.setName(rs.getString("name"));
			lCompanies.add(company);
		}
		conn.close();
		return lCompanies;
	}

	@Override
	public void saveCompany(Company company) throws SQLException,
			ClassNotFoundException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(SAVE_COMPANY);
		ps.setLong(1, company.getCompanyId());
		ps.setString(2, company.getName());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void updateCompany(Company company) throws ClassNotFoundException,
			SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(UPDATE_COMPANY);
		ps.setString(1, company.getName());
		ps.setLong(2, company.getCompanyId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void deleteCompanyById(long companyId) throws SQLException,
			ClassNotFoundException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(DELETE_COMPANY_BY_ID);
		ps.setLong(1, companyId);
		ps.executeUpdate();
		conn.close();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
