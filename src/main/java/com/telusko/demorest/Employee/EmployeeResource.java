package com.telusko.demorest.Employee;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employees")
public class EmployeeResource {
	EmployeeRepository repo = new EmployeeRepository();

	@GET
	@Path("all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Employee> getAllEmployees() {
		return repo.getAll();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Employee getAllEmployees(@PathParam("id") int id) {
		return repo.get(id);
	}

	@POST
	public void addEmployees(Employee... employees) {
		System.out.println("addEmployees");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
//		System.out.println(employee);
		repo.create(employees);
	}
	
	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") int id)
	{
		repo.delete(id);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") int id, Employee employee)
	{
		repo.update(id, employee);
	}
}
