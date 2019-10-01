package com.telusko.demorest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource
{
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Alien> getAliens()
	{
		System.out.println("get alien called");
		return AlienRepository.getAliens();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}")
	public Alien getAlien(@PathParam("id") int id)
	{
		return AlienRepository.getAlien(id);
	}

	@POST
	public void addNewAlien(Alien alien)
	{
		System.out.println(alien);

		AlienRepository.addAlien(alien);
	}
}
