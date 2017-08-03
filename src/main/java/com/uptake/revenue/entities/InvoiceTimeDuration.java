package com.uptake.revenue.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * This is to contain types of invoices and invoices list with in the given time like:(month-to-date, year-to-date). 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceTimeDuration {

	private String title = null;
	private String amount = null;
	private Type types = null;
	private List<Invoice> invoices = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Type getTypes() {
		return types;
	}
	public void setTypes(Type types) {
		this.types = types;
	}
	public List<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
}
