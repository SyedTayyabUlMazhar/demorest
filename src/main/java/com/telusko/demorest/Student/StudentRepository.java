package com.telusko.demorest.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

//			while (resultSet.next())
//			{
//				Student student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
//						resultSet.getInt(4), resultSet.getString(5));
//				
//				students.add(student);
//			}
			students = getStudentsFromResultSet(resultSet);
		} catch (SQLException e)
		{
			printErrorMessage("getAll() Couldn't execute query", e.getMessage());
			e.printStackTrace();

		}
		return students;
	}

	public Student get(int id)
	{
		Student student = new Student();

		ResultSet resultSet;
		try
		{
			String query = "SELECT * FROM students WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			student = getStudentsFromResultSet(resultSet).get(0);
		} catch (SQLException e)
		{
			printErrorMessage("get(int id)", e.getMessage());
			e.printStackTrace();
		}

		return student;
	}

	public void remove(int id)
	{
		String query = "DELETE FROM students WHERE id=?";
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (SQLException e)
		{
			printErrorMessage("couln't remove the student with id : " + id, e.getMessage());
			e.printStackTrace();
		}
	}

	public void insert(Student student)
	{
		String query = "INSERT INTO students VALUES(?,?,?,?,?)";
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirst_name());
			preparedStatement.setString(3, student.last_name);
			preparedStatement.setInt(4, student.getAge());
			preparedStatement.setString(5, student.getClass_name());
			preparedStatement.execute();
		} catch (SQLException e)
		{
			printErrorMessage("couldn't add student : " + student, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void modify(int id, Student student)
	{
		String query = "UPDATE students SET first_name=?, last_name=?, age=?, class_name=? WHERE id=?";
		
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, student.getFirst_name());
			preparedStatement.setString(2, student.getLast_name());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setString(4, student.getClass_name());
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public List<Student> getStudentsFromResultSet(ResultSet resultSet)
	{
		List<Student> students = new ArrayList<Student>();

		try
		{
			while (resultSet.next())
			{
				Student student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getString(5));

				students.add(student);
			}
		} catch (SQLException e)
		{
			printErrorMessage("getStudentsFromResultSet", e.getMessage());
		}
		return students;
	}

	public static void printErrorMessage(String yourMessage, String exceptionMessage)
	{
		System.out.println("ERROR : " + yourMessage + ". Exception Message : " + exceptionMessage);
	}
}
