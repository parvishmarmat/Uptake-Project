package com.uptake.revenue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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