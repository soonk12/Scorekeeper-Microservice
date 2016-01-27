package org.soonk.hero;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonParseException;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class HeroBean {

	private String id;
	
	private String name;
	private int cost;
	private int will;
	private int attack;
	private int defense;
	private int hitPoints;
	private String sphere;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getCost() {
		return cost;
	}



	public void setCost(int cost) {
		this.cost = cost;
	}



	public int getWill() {
		return will;
	}



	public void setWill(int will) {
		this.will = will;
	}



	public int getAttack() {
		return attack;
	}



	public void setAttack(int attack) {
		this.attack = attack;
	}



	public int getDefense() {
		return defense;
	}



	public void setDefense(int defense) {
		this.defense = defense;
	}



	public int getHitPoints() {
		return hitPoints;
	}



	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}



	public String getSphere() {
		return sphere;
	}



	public void setSphere(String sphere) {
		this.sphere = sphere;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	static JsonDocument toJson(HeroBean bean, long counter) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		bean.setId(String.valueOf(counter));
		String json = mapper.writeValueAsString(bean);
		
		return JsonDocument.create("hero_" + bean.getId(), JsonObject.fromJson(json));
	}

	static JsonDocument toJson(HeroBean bean) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bean);
		
		return JsonDocument.create("hero_" + bean.getId(), JsonObject.fromJson(json));
	}
}
