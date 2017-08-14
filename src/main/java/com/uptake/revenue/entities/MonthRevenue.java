package com.uptake.revenue.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthRevenue {
	
	private String userid = null;
	private String totalRevenue = null;
	private String currency = null;
	private List<Type> type;
	private List<Customer> customers = new ArrayList<Customer>();
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<Type> getType() {
		return type;
	}
	public void setType(List<Type> type) {
		this.type = type;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}	
}
