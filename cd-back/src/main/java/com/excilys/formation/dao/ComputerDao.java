package com.excilys.formation.dao;

import java.util.List;

import com.excilys.formation.om.Computer;

public interface ComputerDao {
		
	public Computer getComputerById(long computerId);
	
	public List<Computer> getComputersByName(String name, String s, String o, int p, int n);

	public List<Computer> findAllComputers();
	
	public List<Computer> findAllComputersLimited(String s, String o, int p, int n);

	public long countComputers(String name);
	
	public void saveComputer(Computer computer);

	public void updateComputer(Computer computer);

	public void deleteComputerById(long computerId);
	
}
