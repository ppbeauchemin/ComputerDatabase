package com.excilys.formation.test.conn;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.conn.Conn;

public class ConnTests {
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = Conn.getConnection();
	}

	@After
	public void tearDown() throws Exception {
		conn = null;
	}

	@Test
	public void connTest() {
		assertNotNull(conn);
	}

}
