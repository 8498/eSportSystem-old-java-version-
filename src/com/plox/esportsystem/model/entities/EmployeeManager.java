package com.plox.esportsystem.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.plox.esportsystem.config.Connection;

public class EmployeeManager implements EntityManager {

	Connection connection = new Connection();
	Statement stmt;
	String query;
	
	public EmployeeManager(){}
	
	@Override
	public ArrayList<Employee> getAll() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "SELECT employees.id as employeeID,firstname, lastname, email, birthdate, phone, offices.name as officename FROM employees "
					+ "INNER JOIN office_employee ON employees.id = office_employee.employee_id "
					+ "INNER JOIN offices ON office_employee.office_id = offices.id";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				Employee employee = new Employee();
			
				employee.setId(rs.getInt("employeeID"));
				employee.setFirstname(rs.getString("firstname"));
				employee.setLastname(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setBirthdate(rs.getDate("birthdate"));
				employee.setPhone(rs.getInt("phone"));
				employee.setOffice_name(rs.getString("officename"));
				
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return employeeList;
	}

	@Override
	public Employee get(int id) {
		connection.createConnection();
		
		Employee employee = new Employee();
		
		try {
			stmt = connection.con.createStatement();
			query = "SELECT * FROM employees WHERE id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				employee.setId(rs.getInt("id"));
				employee.setFirstname(rs.getString("firstname"));
				employee.setLastname(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setBirthdate(rs.getDate("birthdate"));
				employee.setPhone(rs.getInt("phone"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		return employee;
	}

	@Override
	public void create(ArrayList<String> data) {
		String firstname = data.get(0);
		String lastname = data.get(1);
		String email = data.get(2);
		String birthdate = data.get(3);
		String phone = data.get(4);
		String office_name = data.get(5);
		
		connection.createConnection();
		try {
			stmt = connection.con.createStatement();
			query = "INSERT INTO `employees` (`firstname`, `lastname`, `email`, `birthdate`, `phone`) VALUES ('"+firstname+"','"+lastname+"','"+email+"','"+birthdate+"','"+phone+"');"
					+ "SET @employee_id = LAST_INSERT_ID();"
					+ "SELECT id INTO @office_id FROM offices WHERE name = '"+office_name+"';"
					+ "INSERT INTO `office_employee` (`employee_id`,`office_id`) VALUES (@employee_id,@office_id);";
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
			query = "UPDATE `employees` SET `"+columnName+"`='"+value+"'WHERE id='"+id+"'";
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
			query = "DELETE FROM `employees` WHERE id='"+id+"'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
	}

}
