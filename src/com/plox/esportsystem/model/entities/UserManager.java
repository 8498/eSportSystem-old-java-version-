package com.plox.esportsystem.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			query = "SELECT users.*,roles.name as rolename FROM users "
					+ "INNER JOIN roles_users ON users.id = roles_users.user_id "
					+ "INNER JOIN roles ON roles_users.role_id = roles.id";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				User user = new User();
			
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setLast_login_date(rs.getTimestamp("lastlogin"));
				user.setRole(rs.getString("rolename"));
				
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
		String role = data.get(3);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		int user_id;
		int role_id;
		
		ResultSet rs;
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			
			query = "INSERT INTO `users` (`login`, `email`, `password`,`lastlogin`) VALUES ('"+login+"', '"+email+"', '"+password+"', '"+dateFormat.format(date)+"')";
			stmt.executeUpdate(query);
			
			rs = stmt.executeQuery("SELECT id FROM users WHERE email = '"+email+"'");
			rs.next();
			user_id = rs.getInt("id");
			
			rs = stmt.executeQuery("SELECT id FROM roles WHERE name = '"+role+"'");
			rs.next();
			role_id = rs.getInt("id");
			
			query = "INSERT INTO `roles_users` (`user_id`,`role_id`) VALUES ("+user_id+","+role_id+")";
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
