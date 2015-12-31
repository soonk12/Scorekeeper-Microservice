package org.soonk.hero;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

@Singleton
@Startup
public class Database {

	CouchbaseCluster cluster;
	Bucket bucket;

	@PostConstruct
	public void init() {

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
			bucket = getCluster().openBucket("hello");
		}
		return bucket;
	}
}
