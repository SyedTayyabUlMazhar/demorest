package com.telusko.demorest.Employee;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employees")
public class EmployeeResource
{
	EmployeeRepository repo = new EmployeeRepository();
	
	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Employee> getAllEmployees()
	{
		return repo.getAllEmployees();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Employee getAllEmployees(@PathParam("id") int id)
	{
		return repo.getEmployee(id);
	}
	
	
	
}
