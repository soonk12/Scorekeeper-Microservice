package org.soonk.hero;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hero")
public class HeroResource {

	@GET
	public String getAll() {
		System.out.println("Get all heros");
		return "getAll()";
	}
	
	@GET
	@Path("{name}")
	public String getHero(@PathParam("name") String name) {
		System.out.println("Getting hero " + name);
		return "getHero(name)";
	}
	
	@POST
	@Consumes("application/json")
	public String addHero(HeroBean hero) {
		
		return "addHero";
	}
	
	// Update
	
	// Delete
	
	// getRandom
}
