package com.excilys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.formation.conn.Conn;
import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.om.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	private Connection conn;

	@Override
	public Company getCompanyById(long companyId) {
		Company company = null;
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(GET_COMPANY_BY_ID);
			ps.setLong(1, companyId);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				company = new Company();
				company.setCompanyId(rs.getLong("id"));
				company.setName(rs.getString("name"));
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<Company> findAllCompanies() {
		List<Company> lCompanies = new ArrayList<Company>();
		try {
			conn = Conn.getConnection();
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lCompanies;
	}

	@Override
	public void saveCompany(Company company) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(SAVE_COMPANY);
			ps.setLong(1, company.getCompanyId());
			ps.setString(2, company.getName());
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCompany(Company company) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE_COMPANY);
			ps.setString(1, company.getName());
			ps.setLong(2, company.getCompanyId());
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCompanyById(long companyId) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(DELETE_COMPANY_BY_ID);
			ps.setLong(1, companyId);
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
