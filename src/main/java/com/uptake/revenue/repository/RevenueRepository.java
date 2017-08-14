/*******************************************************
 * Copyright (C) 2015-2016 TechMahindra
 * 
 * This file is part of InvoiceAndRevenue-SpringBoot.
 * 
 * InvoiceAndRevenue-SpringBoot can not be copied and/or distributed without the express
 * permission.
 *******************************************************/
package com.uptake.revenue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uptake.revenue.entities.Customer;

@Component
@Repository
public interface RevenueRepository extends MongoRepository<Customer, String>{

  public Customer findCurrencyByUserid(String userid);

}
