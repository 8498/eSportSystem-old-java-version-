package com.plox.esportsystem.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.plox.esportsystem.config.Connection;

public class RoleManager implements EntityManager {

	Connection connection = new Connection();
	Statement stmt;
	String query;
	
	public RoleManager(){}
	
	@Override
	public ArrayList<Role> getAll() {
		ArrayList<Role> roleList = new ArrayList<Role>();
		
		connection.createConnection();
		try{
			stmt = connection.con.createStatement();
			query = "SELECT * FROM roles";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				Role role = new Role();
			
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				
				roleList.add(role);
			}
			
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		connection.closeConnection();
		return roleList;
	}

	@Override
	public Object get(int id) {
		connection.createConnection();
		
		Role role = new Role();
		
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM roles WHERE id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return role;
	}

	@Override
	public void create(ArrayList<String> data) {
		String name = data.get(0);
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "INSERT INTO `roles` (`name`) VALUES ('"+name+"')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();

	}

	@Override
	public void edit(int id, String columnName, Object value) {
		connection.createConnection();
		
		try {
			stmt = connection.con.createStatement();
			query = "UPDATE `roles` SET `"+columnName+"`='"+value+"'WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();

	}

	@Override
	public void delete(int id) {
		connection.createConnection();
		
		try {
			stmt = connection.con.createStatement();
			query = "DELETE FROM `roles` WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();

	}
	
	public ArrayList<String> getAllName()
	{
		Iterator<Role> iterator = getAll().iterator();
		ArrayList<String> arraylist = new ArrayList<String>();
		
		while(iterator.hasNext())
		{
			arraylist.add(iterator.next().getName());
		}
		return arraylist;
	}

}
