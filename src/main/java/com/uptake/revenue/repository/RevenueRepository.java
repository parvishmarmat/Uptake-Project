/*******************************************************
 * Copyright (C) 2015-2016 TechMahindra
 * 
 * This file is part of KafkaAdminAPI-SpringBoot.
 * 
 * KafkaAdminAPI-SpringBoot can not be copied and/or distributed without the express
 * permission.
 *******************************************************/
package com.uptake.revenue.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.uptake.revenue.entities.Revenue;

@Component
@Repository
public interface RevenueRepository extends MongoRepository<Revenue, String>{
  
  public Revenue findByUser(String user);
  
  /*@Query(value = "{'date':{ $lt: ?0, $gt: ?1}}")
  List<Revenue> findByDateBetween(String from, String to);*/
  
}
