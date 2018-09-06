package com.logevents.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDetails {

	public Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/Test", "SA", "");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
