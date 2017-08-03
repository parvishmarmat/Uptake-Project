package com.uptake.revenue.service;

import org.springframework.stereotype.Component;

import com.uptake.revenue.entities.User;

@Component
public interface IRevenueService {

	/**	
	 * Service to create the Order
	 * @param service
	 * @return
	 * @throws Exception
	 */
	User loginApi(User user);
}
