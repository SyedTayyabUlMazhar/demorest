package com.telusko.demorest.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

public class EmployeeRepository
{
	Connection connection;

	public EmployeeRepository()
	{
		String url = "jdbc:oracle:thin:hr/hr@localhost:1521:xe";

		try
		{
			OracleDataSource dataSource = new OracleDataSource();
			dataSource.setURL(url);

			connection = dataSource.getConnection();
		} catch (Exception e)
		{
			System.out.println("Error : Connection, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees()
	{
		List<Employee> list = new ArrayList<Employee>();

		String query = "SELECT * FROM employees";

		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next())
			{
				Employee employee = new Employee(resultSet.getInt(0), resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6),
						resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10));

				list.add(employee);
			}

		} catch (Exception e)
		{
			System.out.println("Error : getAllEmployees, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}

	public Employee getEmployee(int id)
	{
		Employee employee = new Employee();

		String query = "SELECT * FROM employees WhERE employee_id=" + id;

		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			if (resultSet.next())
			{
//				employee = new Employee(resultSet.getInt(0), resultSet.getString(1), resultSet.getString(2),
//						resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6),
//						resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10));
				
				employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
			}
			
			System.out.println(employee.toString());

		} catch (Exception e)
		{
			System.out.println("Error : getEmployee, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}

		return employee;

	}
}
