package com.excilys.formation.request;

public interface ComputerRequest {
	String GET_COMPUTER_BY_ID = "select * from computer left join company on computer.company_id = company.id where computer.id = ?";
	String GET_COMPUTERS_BY_NAME = "select * from computer left join company on computer.company_id = company.id where computer.name like ? order by %s is null, %s %s limit ?, ?";
	String FIND_ALL_COMPUTERS = "select * from computer left join company on computer.company_id = company.id order by computer.name asc";
	String FIND_ALL_COMPUTERS_LIMITED = "select * from computer left join company on computer.company_id = company.id order by %s is null, %s %s limit ?, ?";
	String COUNT_COMPUTERS = "select count(*) as cpt from computer left join company on computer.company_id = company.id where computer.name like ?";
	String SAVE_COMPUTER = "insert into computer values(?, ?, ?, ?, ?)";
	String UPDATE_COMPUTER = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	String DELETE_COMPUTER_BY_ID = "delete from computer where id = ?";
}