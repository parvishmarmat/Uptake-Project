package com.uptake.revenue.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateBasedRevenue {
	
	private String timePeriod = null;
	private String totalRevenue = null;
	
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
}
