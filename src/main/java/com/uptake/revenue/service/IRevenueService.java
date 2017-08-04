package com.uptake.revenue.service;

import org.springframework.stereotype.Component;

import com.uptake.revenue.entities.Revenue;
import com.uptake.revenue.entities.User;

@Component
public interface IRevenueService {

	/**	
	 * Service to get revenue based on user
	 * @param service
	 * @return
	 * @throws Exception
	 */
	User loginApi(User user);
	Revenue revenueApi(Revenue revenue);
}
