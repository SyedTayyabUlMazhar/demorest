package com.telusko.demorest.Student;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentResource
{
	private StudentRepository repository;
	
	public StudentResource()
	{
		repository = new StudentRepository();
	}

	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Student> getStudents()
	{
		System.out.println("getStudents");
		return repository.getAll();
	}
}
