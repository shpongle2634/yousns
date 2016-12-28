package com.yousns.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBConnect {
	
	
public Connection getConnection() throws SQLException{

		String url="jdbc:oracle:thin:@210.94.199.20:1521:dblab";
		String user= "ST2010112469";
		String pass = "ST2010112469";
		return DriverManager.getConnection(url,user,pass);
		
	}

public void close(Connection conn,Statement psmt,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(psmt != null){
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
public void close(Connection conn,Statement psmt){
	if(psmt != null){
		try {
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn != null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	public DBConnect() {
		try {
			String driver= "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
