/*******************************************************
 * Copyright (C) 2015-2016 TechMahindra
 * 
 * This file is part of KafkaAdminAPI-SpringBoot.
 * 
 * KafkaAdminAPI-SpringBoot can not be copied and/or distributed without the express
 * permission.
 *******************************************************/
package com.uptake.revenue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uptake.revenue.entities.User;

@Component
@Repository
public interface UserRepository extends MongoRepository<User, String>{
  
  public User findByUsername(String username);
  
}
