package org.soonk.hero;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

@Path("hero")
public class HeroResource {

	@Inject Database database;
	
	@GET
	public String getAll() {
		System.out.println("Get all heros");
		
		 N1qlQuery query = N1qlQuery
	                .simple(select("*")
	                .from(i(database.getBucket().name()))
	                .limit(10));
	        System.out.println(query.statement().toString());
	        N1qlQueryResult result = database.getBucket().query(query);
	        System.err.println(result.errors());
	        System.out.println(result.toString());
	        return result.allRows().toString();
	}
	
	@GET
	@Path("{name}")
	public String getHero(@PathParam("name") String name) {
		System.out.println("Getting hero " + name);
		
		N1qlQuery query = N1qlQuery.simple("SELECT * from scorekeeper WHERE name = '" + name + "'");
		System.out.println(query.statement().toString());
		N1qlQueryResult result = database.getBucket().query(query);
		if (result.finalSuccess() && !result.allRows().isEmpty()) {
			return result.allRows().get(0).toString();
		}
		
		return "getHero(name)";
	}
	
	@POST
	@Consumes("application/json")
	public String addHero(HeroBean hero) throws JsonProcessingException {
		System.out.println("POST: " + hero);
		JsonLongDocument id = database.getBucket().counter("hero_sequence", 1);
		
		JsonDocument document = database.getBucket().insert(HeroBean.toJson(hero, id.content().longValue()));
		return document.content().toString();
	}
	
	// Update
	
	// Delete
	
	// getRandom
}
