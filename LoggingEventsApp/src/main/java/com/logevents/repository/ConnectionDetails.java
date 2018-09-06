package com.logevents.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDetails {

	public Connection getConnection() {
		Connection con = null;

		try {
			
			File src = new File("Config_db.properties");
			FileInputStream fis = new FileInputStream(src);
			Properties prop = new Properties();
			prop.load(fis);

			

			Class.forName(prop.getProperty("drivername"));

			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("uname"), prop.getProperty(""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
	}

}
