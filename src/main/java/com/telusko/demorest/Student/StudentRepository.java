package com.telusko.demorest.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

public class StudentRepository
{

	private Connection connection;

	public StudentRepository()
	{
		String url = "jdbc:oracle:thin:hr/hr@localhost:1521:xe";

		try
		{
			OracleDataSource dataSource = new OracleDataSource();
			dataSource.setURL(url);

			connection = dataSource.getConnection();
		} catch (SQLException e)
		{
			printErrorMessage("Unable to get connection.", e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Student> getAll()
	{
		List<Student> students = new ArrayList<Student>();

		try
		{
			String query = "SELECT * FROM students";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next())
			{
				Student student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getString(5));
				
				students.add(student);
			}
		} catch (SQLException e)
		{
			printErrorMessage("getAll() Couldn't execute query", e.getMessage());
			e.printStackTrace();

		}
		return students;
	}

	public static void printErrorMessage(String yourMessage, String exceptionMessage)
	{
		System.out.println("ERROR : " + yourMessage + ". Exception Message : " + exceptionMessage);
	}
}
