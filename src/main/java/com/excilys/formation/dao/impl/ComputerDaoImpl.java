package com.excilys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.formation.conn.Conn;
import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;

@Repository
public class ComputerDaoImpl implements ComputerDao {
	private Connection conn;

	@Override
	public Computer getComputerById(long computerId)
			throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(GET_COMPUTER_BY_ID);
		ps.setLong(1, computerId);
		ResultSet rs = ps.executeQuery();
		Company company = null;
		Computer computer = null;
		if (rs.first()) {
			company = null;
			computer = new Computer();
			computer.setComputerId(rs.getLong("computer.id"));
			computer.setName(rs.getString("computer.name"));
			computer.setIntroduced(rs.getTimestamp("computer.introduced"));
			computer.setDiscontinued(rs.getTimestamp("computer.discontinued"));
			if (rs.getLong("company.id") != 0) {
				company = new Company();
				company.setCompanyId(rs.getLong("company.id"));
				company.setName(rs.getString("company.name"));
			}
			computer.setCompany(company);
		}
		conn.close();
		return computer;
	}

	@Override
	public List<Computer> getComputersByName(String name, String s, String o, int p, int n)
			throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		List<Computer> lComputers = new ArrayList<Computer>();
		PreparedStatement ps = conn.prepareStatement(String.format(
				GET_COMPUTERS_BY_NAME, s, s, o));
		ps.setString(1, "%" + name + "%");
		ps.setInt(2, p);
		ps.setInt(3, n);
		ResultSet rs = ps.executeQuery();
		Company company = null;
		Computer computer = null;
		while (rs.next()) {
			company = null;
			computer = new Computer();
			computer.setComputerId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
			computer.setIntroduced(rs.getTimestamp("introduced"));
			computer.setDiscontinued(rs.getTimestamp("discontinued"));
			if (rs.getLong("company.id") != 0) {
				company = new Company();
				company.setCompanyId(rs.getLong("company.id"));
				company.setName(rs.getString("company.name"));
			}
			computer.setCompany(company);
			lComputers.add(computer);
		}
		conn.close();
		return lComputers;
	}

	@Override
	public List<Computer> findAllComputers() throws ClassNotFoundException,
			SQLException {
		conn = Conn.getConnection();
		List<Computer> lComputers = new ArrayList<Computer>();
		PreparedStatement ps = conn.prepareStatement(FIND_ALL_COMPUTERS);
		ResultSet rs = ps.executeQuery();
		Company company = null;
		Computer computer = null;
		while (rs.next()) {
			company = null;
			computer = new Computer();
			computer.setComputerId(rs.getLong("computer.id"));
			computer.setName(rs.getString("name"));
			computer.setIntroduced(rs.getTimestamp("computer.introduced"));
			computer.setDiscontinued(rs.getTimestamp("computer.discontinued"));
			if (rs.getLong("company.id") != 0) {
				company = new Company();
				company.setCompanyId(rs.getLong("company.id"));
				company.setName(rs.getString("company.name"));
			}
			computer.setCompany(company);
			lComputers.add(computer);
		}
		conn.close();
		return lComputers;
	}

	@Override
	public List<Computer> findAllComputersLimited(String s, String o, int p,
			int n) throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		List<Computer> lComputers = new ArrayList<Computer>();
		PreparedStatement ps = conn.prepareStatement(String.format(
				FIND_ALL_COMPUTERS_LIMITED, s, s, o));
		ps.setInt(1, p);
		ps.setInt(2, n);
		ResultSet rs = ps.executeQuery();
		Company company = null;
		Computer computer = null;
		while (rs.next()) {
			company = null;
			computer = new Computer();
			computer.setComputerId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
			computer.setIntroduced(rs.getTimestamp("introduced"));
			computer.setDiscontinued(rs.getTimestamp("discontinued"));
			if (rs.getLong("company.id") != 0) {
				company = new Company();
				company.setCompanyId(rs.getLong("company.id"));
				company.setName(rs.getString("company.name"));
			}
			computer.setCompany(company);
			lComputers.add(computer);
		}
		conn.close();
		return lComputers;
	}

	@Override
	public int countComputers(String name) throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(COUNT_COMPUTERS);
		ps.setString(1, "%"+name+"%");
		ResultSet rs = ps.executeQuery();
		rs.first();
		return rs.getInt(1);
	}

	@Override
	public void saveComputer(Computer computer) throws ClassNotFoundException,
			SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(SAVE_COMPUTER);
		ps.setLong(1, computer.getComputerId());
		ps.setString(2, computer.getName());
		ps.setTimestamp(3, computer.getIntroduced());
		ps.setTimestamp(4, computer.getDiscontinued());
		if (computer.getCompany() != null) {
			ps.setLong(5, computer.getCompany().getCompanyId());
		} else {
			ps.setLong(5, 0l);
		}
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void updateComputer(Computer computer)
			throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(UPDATE_COMPUTER);
		ps.setString(1, computer.getName());
		ps.setTimestamp(2, computer.getIntroduced());
		ps.setTimestamp(3, computer.getDiscontinued());
		if (computer.getCompany() != null) {
			ps.setLong(4, computer.getCompany().getCompanyId());
		} else {
			ps.setLong(4, 0l);
		}
		ps.setLong(5, computer.getComputerId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void deleteComputerById(long computerId)
			throws ClassNotFoundException, SQLException {
		conn = Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement(DELETE_COMPUTER_BY_ID);
		ps.setLong(1, computerId);
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
