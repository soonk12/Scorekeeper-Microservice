package org.soonk.hero;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

@Singleton
@Startup
public class Database {

	CouchbaseCluster cluster;
	Bucket bucket;

	@PostConstruct
	public void init() {
		System.out.println("Database.init()");
		if (!getBucket().exists("hero_sequence")) {
			System.out.println("Creating hero-sequence");
			// N1qlQuery query = N1qlQuery.simple("SELECT MAX(id) + 1 as
			// counterInit FROM `scorekeeper` where type=\"hero\"");
			// N1qlQueryResult result = bucket.query(query);
			// System.out.println(result.errors());
			// if (result.finalSuccess()) {
			// System.out.println("Creating hero-sequence");
			// long counterInit =
			// result.allRows().get(0).value().getLong("counterInit");
			bucket.insert(JsonLongDocument.create("hero_sequence", 1L));
			// }
		}
	}

	@PreDestroy
	public void stop() {
		bucket.close();
		cluster.disconnect();
	}

	public CouchbaseCluster getCluster() {
		if (null == cluster) {
			cluster = CouchbaseCluster.create(System.getenv("COUCHBASE_URI"));
		}
		return cluster;
	}

	public Bucket getBucket() {
		if (null == bucket) {
			bucket = getCluster().openBucket("scorekeeper");
		}
		return bucket;
	}
}
