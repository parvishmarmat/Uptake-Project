package com.uptake.revenue.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uptake.revenue.util.Constants;

@Document(collection = Constants.REVENUE_COLLECTION)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousYear {
	
	private String year = null;
	private String revenue = null;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	
	
}
