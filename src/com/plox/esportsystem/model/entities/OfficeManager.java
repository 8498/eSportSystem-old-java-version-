package com.plox.esportsystem.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.plox.esportsystem.supply.Connection;

public class OfficeManager implements EntityManager {

	Connection connection = new Connection();
	Statement stmt;
	String query;
	
	@Override
	public ArrayList<Office> getAll() {
ArrayList<Office> officeList = new ArrayList<Office>();
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM offices ";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				Office office = new Office();
			
				office.setId(rs.getInt("id"));
				office.setName(rs.getString("name"));
				
				officeList.add(office);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return officeList;
	}

	@Override
	public Office get(int id) {
		connection.createConnection();
		
		Office office = new Office();
		
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM offices WHERE id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				office.setId(rs.getInt("id"));
				office.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return office;
	}

	@Override
	public void create(ArrayList<String> data) {
		String name = data.get(0);
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "INSERT INTO `offices` (`name`) VALUES ('"+name+"')";
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
			query = "UPDATE `offices` SET `"+columnName+"`='"+value+"'WHERE id='"+id+"'";
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
			query = "DELETE FROM `offices` WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();

	}

}
