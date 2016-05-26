package kr.ranian.web;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/book_ex2";
	public static final String USER = "ranian";
	public static final String PW = "sell99";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW) ) {
			System.out.println("## connection:" + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
