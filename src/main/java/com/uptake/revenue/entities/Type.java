package com.uptake.revenue.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Types of revenue entry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

	private String sales = null;
	private String rental = null;
	private String parts = null;
	private String labor = null;
	
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getRental() {
		return rental;
	}
	public void setRental(String rental) {
		this.rental = rental;
	}
	public String getParts() {
		return parts;
	}
	public void setParts(String parts) {
		this.parts = parts;
	}
	public String getLabor() {
		return labor;
	}
	public void setLabor(String labor) {
		this.labor = labor;
	}
	
	
}
