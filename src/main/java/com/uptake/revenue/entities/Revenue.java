package com.uptake.revenue.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Revenue {
	
	private String userid = null;
	private String monthToDate = null;
	private String quarterToDate = null;
	private String yearToDate = null;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMonthToDate() {
		return monthToDate;
	}
	public void setMonthToDate(String monthToDate) {
		this.monthToDate = monthToDate;
	}
	public String getQuarterToDate() {
		return quarterToDate;
	}
	public void setQuarterToDate(String quarterToDate) {
		this.quarterToDate = quarterToDate;
	}
	public String getYearToDate() {
		return yearToDate;
	}
	public void setYearToDate(String yearToDate) {
		this.yearToDate = yearToDate;
	}
	
}
