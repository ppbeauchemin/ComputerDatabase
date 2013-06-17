package com.excilys.formation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.conn.Conn;
import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.om.Company;
import com.excilys.formation.om.Computer;

@Repository
public class ComputerDaoImpl implements ComputerDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Connection conn;

	@Override
	public Computer getComputerById(long computerId) {
		Computer computer = null;
		Company company = null;
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(GET_COMPUTER_BY_ID);
			ps.setLong(1, computerId);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				company = null;
				computer = new Computer();
				computer.setComputerId(rs.getLong("computer.id"));
				computer.setName(rs.getString("computer.name"));
				computer.setIntroduced(rs.getDate("computer.introduced"));
				computer.setDiscontinued(rs.getDate("computer.discontinued"));
				if (rs.getLong("company.id") != 0) {
					company = new Company();
					company.setCompanyId(rs.getLong("company.id"));
					company.setName(rs.getString("company.name"));
				}
				computer.setCompany(company);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> getComputersByName(String name, String s, String o,
			int p, int n) {
		List<Computer> lComputers = new ArrayList<Computer>();
		Company company = null;
		Computer computer = null;
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(String.format(
					GET_COMPUTERS_BY_NAME, s, s, o));
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, p);
			ps.setInt(3, n);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = null;
				computer = new Computer();
				computer.setComputerId(rs.getLong("id"));
				computer.setName(rs.getString("name"));
				computer.setIntroduced(rs.getDate("introduced"));
				computer.setDiscontinued(rs.getDate("discontinued"));
				if (rs.getLong("company.id") != 0) {
					company = new Company();
					company.setCompanyId(rs.getLong("company.id"));
					company.setName(rs.getString("company.name"));
				}
				computer.setCompany(company);
				lComputers.add(computer);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lComputers;
	}

	@Override
	public List<Computer> findAllComputers() {
		List<Computer> lComputers = new ArrayList<Computer>();
		Company company = null;
		Computer computer = null;
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(FIND_ALL_COMPUTERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = null;
				computer = new Computer();
				computer.setComputerId(rs.getLong("computer.id"));
				computer.setName(rs.getString("name"));
				computer.setIntroduced(rs.getDate("computer.introduced"));
				computer.setDiscontinued(rs.getDate("computer.discontinued"));
				if (rs.getLong("company.id") != 0) {
					company = new Company();
					company.setCompanyId(rs.getLong("company.id"));
					company.setName(rs.getString("company.name"));
				}
				computer.setCompany(company);
				lComputers.add(computer);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lComputers;
	}

	@Override
	public List<Computer> findAllComputersLimited(String s, String o, int p,
			int n) {
		List<Computer> lComputers = new ArrayList<Computer>();
		Company company = null;
		Computer computer = null;
		try {
			conn = Conn.getConnection();

			PreparedStatement ps = conn.prepareStatement(String.format(
					FIND_ALL_COMPUTERS_LIMITED, s, s, o));
			ps.setInt(1, p);
			ps.setInt(2, n);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = null;
				computer = new Computer();
				computer.setComputerId(rs.getLong("id"));
				computer.setName(rs.getString("name"));
				computer.setIntroduced(rs.getDate("introduced"));
				computer.setDiscontinued(rs.getDate("discontinued"));
				if (rs.getLong("company.id") != 0) {
					company = new Company();
					company.setCompanyId(rs.getLong("company.id"));
					company.setName(rs.getString("company.name"));
				}
				computer.setCompany(company);
				lComputers.add(computer);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lComputers;
	}

	@Override
	public int countComputers(String name) {
		int nb = 0;
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(COUNT_COMPUTERS);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			rs.first();
			nb = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return nb;
	}

	@Override
	public void saveComputer(Computer computer) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(SAVE_COMPUTER);
			ps.setLong(1, computer.getComputerId());
			ps.setString(2, computer.getName());
			ps.setDate(3, computer.getIntroduced());
			ps.setDate(4, computer.getDiscontinued());
			if (computer.getCompany() != null) {
				ps.setLong(5, computer.getCompany().getCompanyId());
			} else {
				ps.setLong(5, 0l);
			}
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateComputer(Computer computer) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE_COMPUTER);
			ps.setString(1, computer.getName());
			ps.setDate(2, computer.getIntroduced());
			ps.setDate(3, computer.getDiscontinued());
			if (computer.getCompany() != null) {
				ps.setLong(4, computer.getCompany().getCompanyId());
			} else {
				ps.setLong(4, 0l);
			}
			ps.setLong(5, computer.getComputerId());
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComputerById(long computerId) {
		try {
			conn = Conn.getConnection();
			PreparedStatement ps = conn.prepareStatement(DELETE_COMPUTER_BY_ID);
			ps.setLong(1, computerId);
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
