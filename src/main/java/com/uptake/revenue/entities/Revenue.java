package com.uptake.revenue.entities;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Contains revenue details for the user(dealer).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Revenue {

	@Id
	private String id = null;
	private String user = null;
	private InvoiceTimeDuration timeDuration = null;
	
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
	public InvoiceTimeDuration getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(InvoiceTimeDuration timeDuration) {
		this.timeDuration = timeDuration;
	}
	
}
