package com.uptake.revenue.entities;

import java.util.Date;

import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * User Entity.
 */
@Document(collection = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	private String userid = null;
	private String username = null;
	private String password = null;
	private String firstname = null;
	private String lastname = null;
	private String email = null;
	private String contact = null;
	private String address = null;
	
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private DateTime createdDate = new DateTime();
	
	@DateTimeFormat(style="MM/dd/yyyy")
	private Date date = new Date();
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserid() {
		return userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
