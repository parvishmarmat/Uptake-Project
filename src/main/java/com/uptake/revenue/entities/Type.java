package com.uptake.revenue.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Types of revenue entry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

	private String type = null;
	private String revenue = null;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
}
