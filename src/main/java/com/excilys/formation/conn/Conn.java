package com.excilys.formation.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conn {

	static final String URL = "jdbc:mysql://localhost/computer_database?zeroDateTimeBehavior=convertToNull";
	static final String USER = "pp";
	static final String PASSWORD = "root";
	static final String DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}

}
