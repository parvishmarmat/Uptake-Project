package com.uptake.revenue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This is the start point for the application. 
 * 
 * @author PM00474968
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.uptake.revenue" })
public class InvoicAndRevenueRestApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(InvoicAndRevenueRestApiApplication.class, args);
	}
}