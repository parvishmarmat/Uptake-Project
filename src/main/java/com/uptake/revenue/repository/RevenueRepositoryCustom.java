package com.uptake.revenue.repository;

import org.springframework.stereotype.Component;

@Component
public interface RevenueRepositoryCustom {
	
	String getRevenueByDateRange(String userId, String firstDate, String lastDate);
}
