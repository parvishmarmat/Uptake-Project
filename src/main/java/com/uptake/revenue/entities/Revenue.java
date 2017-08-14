package com.uptake.revenue.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Revenue {
	
	private String userid = null;
	private String currency = null;
	List<DateBasedRevenue> revenue = new ArrayList<DateBasedRevenue>();
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<DateBasedRevenue> getRevenue() {
		return revenue;
	}
	public void setRevenue(List<DateBasedRevenue> revenue) {
		this.revenue = revenue;
	}
	
}
