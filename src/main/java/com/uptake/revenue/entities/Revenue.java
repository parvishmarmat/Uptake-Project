package com.uptake.revenue.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Contains revenue details for the user(dealer).
 */
@Document(collection = "revenue")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Revenue {

	@Id
	private String id = null;
	private String user = null;
	private TimeDuration timeDuration = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public TimeDuration getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(TimeDuration timeDuration) {
		this.timeDuration = timeDuration;
	}
}
