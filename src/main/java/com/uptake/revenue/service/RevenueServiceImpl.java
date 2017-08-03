package com.uptake.revenue.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uptake.revenue.entities.User;
import com.uptake.revenue.exception.BadArgumentException;
import com.uptake.revenue.repository.UserRepository;
import com.uptake.revenue.util.Constants;

@org.springframework.stereotype.Service
public class RevenueServiceImpl implements IRevenueService{
	
	@Autowired
	UserRepository userRepository;
	
	public User loginApi(User user){
		User userNew = userRepository.findByUserName(user.getUserName().trim());
		if (userNew==null) {
			throw new BadArgumentException(Constants.USER_NOT_EXIST);
		}else if (!userNew.getPassword().equals(user.getPassword())) {
			throw new BadArgumentException(Constants.WRONG_PASSWORD);
		}
		return userNew; 
	}
}
