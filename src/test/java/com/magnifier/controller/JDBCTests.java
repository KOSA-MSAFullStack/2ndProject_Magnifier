package com.magnifier.controller;

import static org.junit.Assert.fail;
import org.junit.Test;
import lombok.extern.log4j.Log4j;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class JDBCTests {
	@Test
	public void TestConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "zoomin", "zoomin");
			log.info(con);
		} catch (SQLException e) {
			fail(e.getMessage());
		} // end try
	}// end test
}// end class