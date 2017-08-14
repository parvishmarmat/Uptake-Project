package com.uptake.revenue.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uptake.revenue.entities.Customer;

@Component
public interface RevenueRepositoryCustom {
	
	String findRevenueByDateRange(String userId, String firstDate, String lastDate);
	String findRevenueByYear(String userId, String firstDate, String lastDate);
	String findRevenueByType(String userId, String firstDate, String lastDate, String type);
	List<Customer> findCustomersByUserIdAndDate(String userId,String firstDate,String lastDate);
}
