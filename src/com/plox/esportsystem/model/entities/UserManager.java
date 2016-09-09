package com.plox.esportsystem.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.plox.esportsystem.services.Authentication;
import com.plox.esportsystem.config.Connection;

public class UserManager implements EntityManager {
	
	Connection connection = new Connection();
	Authentication auth = new Authentication();
	Statement stmt;
	String query;
	
	public UserManager(){}

	public ArrayList<User> getAll() {
		ArrayList<User> userList = new ArrayList<User>();
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				User user = new User();
			
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_date(rs.getTimestamp("lastlogin"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return userList;
	}

	public User get(int id) {
		connection.createConnection();
		
		User user = new User();
		
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM users WHERE id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_date(rs.getTimestamp("lastlogin"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return user;
	}

	public void create(ArrayList<String> data) {
		String login = data.get(0);
		String email = data.get(1);
		String password = auth.hash(data.get(2));
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "INSERT INTO `users` (`login`, `email`, `password`) VALUES ('"+login+"','"+email+"','"+password+"')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	public void edit(int id, String columnName, Object value) {
		connection.createConnection();
		
		try {
			stmt = connection.con.createStatement();
			query = "UPDATE `users` SET `"+columnName+"`='"+value+"'WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
	}

	public void delete(int id) {
		connection.createConnection();
		
		try {
			stmt = connection.con.createStatement();
			query = "DELETE FROM `users` WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	User loggedUser;
	public User verify (String login, String password){
		Iterator<User> iterator = getAll().iterator();
		while(iterator.hasNext())
		{
			User user = (User) iterator.next();
			if(auth.hash(password).equals(user.getPassword()))
			{
				if(login.equals(user.getLogin()))
				{
					loggedUser = user;
					break;
				}
			}
		}
		return loggedUser;
	}
}
