package com.summer.tech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public void test() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/springboot", "hjc", "hjc");
			System.out.println(conn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
