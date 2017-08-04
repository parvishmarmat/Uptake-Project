package com.uptake.revenue.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uptake.revenue.entities.Revenue;
import com.uptake.revenue.entities.User;
import com.uptake.revenue.exception.BadArgumentException;
import com.uptake.revenue.exception.ResourceNotFoundException;
import com.uptake.revenue.repository.RevenueRepository;
import com.uptake.revenue.repository.UserRepository;
import com.uptake.revenue.util.Constants;

@org.springframework.stereotype.Service
public class RevenueServiceImpl implements IRevenueService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RevenueRepository revenueRepository;
	
	public User loginApi(User user){
		User userNew = userRepository.findByUsername(user.getUsername().trim());
		if (userNew==null) {
			throw new BadArgumentException(Constants.USER_NOT_EXIST);
		}else if (!userNew.getPassword().equals(user.getPassword())) {
			throw new BadArgumentException(Constants.WRONG_PASSWORD);
		}
		return userNew; 
	}
	
	public Revenue revenueApi(Revenue revenue){
		Revenue newRevenue = revenueRepository.findByUser(revenue.getUser());
		if (newRevenue==null) {
			throw new ResourceNotFoundException(Constants.DATA_NOT_EXIST);
		}
		return newRevenue;
	}
}
