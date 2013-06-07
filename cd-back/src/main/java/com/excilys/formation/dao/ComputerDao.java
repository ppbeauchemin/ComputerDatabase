package com.excilys.formation.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.om.Computer;
import com.excilys.formation.request.ComputerRequest;

public interface ComputerDao extends ComputerRequest {
		
	public Computer getComputerById(long computerId) throws ClassNotFoundException, SQLException;
	
	public List<Computer> getComputersByName(String name, String s, String o, int p, int n) throws ClassNotFoundException, SQLException;

	public List<Computer> findAllComputers() throws ClassNotFoundException, SQLException;
	
	public List<Computer> findAllComputersLimited(String s, String o, int p, int n) throws ClassNotFoundException, SQLException;

	public int countComputers(String name) throws ClassNotFoundException, SQLException;
	
	public void saveComputer(Computer computer) throws ClassNotFoundException, SQLException;

	public void updateComputer(Computer computer) throws ClassNotFoundException, SQLException;

	public void deleteComputerById(long computerId) throws ClassNotFoundException, SQLException;
	
}
