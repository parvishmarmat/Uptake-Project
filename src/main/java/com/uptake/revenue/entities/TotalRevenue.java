package com.uptake.revenue.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uptake.revenue.util.Constants;

@Document(collection = Constants.REVENUE_COLLECTION)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalRevenue {
	
	@Id
	private String userid = null;
	private String totalRevenue = null;
	private String currency = null;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalrevenue) {
		this.totalRevenue = totalrevenue;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
