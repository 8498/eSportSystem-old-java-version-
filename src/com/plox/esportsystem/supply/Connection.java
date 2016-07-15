package com.plox.esportsystem.supply;

import java.sql.DriverManager;

public class Connection
{
	private String server = "localhost";
	private int port = 3307;
	private String user = "root";
	private String password = "123";
	private String database = "esport";
	
	public java.sql.Connection con;
	
	public void createConnection() 
	{
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("# - Driver Loaded");
			
			String jdbcUrl = "jdbc:mysql://"+server+":"+port+"/"+database+"";

		    con = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("# - Connection Obtained");
			
	}catch (Exception e) {
		System.out.println("# - Error database connection" + e); 
		}
	}
	public void closeConnection()
	{
		if (con != null) 
		{ 
			try { 
				con.close(); 
				System.out.println("# - Closing the database connection"); 
			}catch (Exception e) { 
				System.out.println("# - Error when closing the database connection");
			}
		}
	}
}
