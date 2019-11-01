package com.telusko.demorest.Student;


import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentResource
{
	private StudentRepository repository;
	
	public StudentResource()
	{
		System.out.println("StudentResource.java constructor StudentResource() called.");
		repository = new StudentRepository();
	}

	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Student> getStudents()
	{
		System.out.println("StudentResource.java method getStudents() called.");
		return repository.getAll();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Student getStudent(@PathParam("id") int id)
	{
		System.out.println("StudentResource.java method getStudent(@PathParam(\"id\") int id) called.");
		return repository.get(id);
	}
	
	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") int id)
	{
		System.out.println("StudentResource.java method remove(@PathParam(\"id\") int id) called.");
		repository.remove(id);
	}
	
	@POST
	public void add(Student student)
	{
		System.out.println("StudentResource.java method add(Student student) called.");
		repository.insert(student);
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") int id, Student student)
	{
		System.out.println("StudentResource.java method update(@PathParam(\"id\") int id, Student student) called.");
		System.out.println("To modify id : " + id + "  New Values : " + student );
		repository.modify(id, student);
	}
}
