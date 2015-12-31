package org.soonk.hero;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

@Path("hello")
public class HelloResource {

	@Inject
	Database database;

	@GET
	public String sayHello() {
		// return "Hello World";
		N1qlQuery query = N1qlQuery.simple(select("*").from(i(database.getBucket().name())).limit(10));
		N1qlQueryResult result = database.getBucket().query(query);
		return result.allRows().toString();
	}

}
