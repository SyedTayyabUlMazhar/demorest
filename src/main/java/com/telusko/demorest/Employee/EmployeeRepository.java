package com.telusko.demorest.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

public class EmployeeRepository {
	public final String[] COLUMNS = { "EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE_NUMBER", "HIRE_DATE",
			"JOB_ID", "SALARY", "COMMISSION_PCT", "MANAGER_ID", "DEPARTMENT_ID" };

	Connection connection;

	public EmployeeRepository() {
		String url = "jdbc:oracle:thin:hr/hr@localhost:1521:xe";

		try {
			OracleDataSource dataSource = new OracleDataSource();
			dataSource.setURL(url);

			connection = dataSource.getConnection();
		} catch (Exception e) {
			System.out.println("Error : Connection, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<Employee>();

		String query = "SELECT * FROM employees";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7),
						resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getInt(10), resultSet.getInt(11));

				list.add(employee);
			}

		} catch (Exception e) {
			System.out.println("Error : getAllEmployees, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}

	public Employee get(int id)

	{
		Employee employee = new Employee();

		String query = "SELECT * FROM employees WhERE employee_id=" + id;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7),
						resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getInt(10), resultSet.getInt(11));
			}

			System.out.println(employee.toString());

		} catch (Exception e) {
			System.out.println("Error : getEmployee, MESSAE:" + e.getMessage());
			e.printStackTrace();
		}

		return employee;

	}

	public void create(Employee... employees) {
		try {
			for (Employee employee : employees) {

				String sqlString = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setInt(1, employee.getId());
				preparedStatement.setString(2, employee.getFirstName());
				preparedStatement.setString(3, employee.getLastName());
				preparedStatement.setString(4, employee.getEmail());
				preparedStatement.setString(5, employee.getPhoneNo());
				preparedStatement.setDate(6, employee.getHireDate());
				preparedStatement.setString(7, employee.getJobId());
				preparedStatement.setDouble(8, employee.getSalary());
				preparedStatement.setDouble(9, employee.getCommissionPct());
				preparedStatement.setInt(10, employee.getManagerID());
				preparedStatement.setInt(11, employee.getDepartmentId());

				preparedStatement.executeUpdate();

				System.out.println("createEmployee : SUCCESSFULL");
			}
		} catch (Exception e) {
			System.out.println("createEmployee : FAILED : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			String deleteQuery = "DELETE FROM employees WHERE employee_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("DELTE SUCCESSFUL");
		} catch (Exception e) {
			System.out.println("DELTE FAILED : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
