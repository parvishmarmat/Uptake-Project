/*******************************************************
 * Copyright (C) 2015-2016 TechMahindra
 * 
 * This file is part of InvoiceAndRevenue-SpringBoot.
 * 
 * InvoiceAndRevenue-SpringBoot can not be copied and/or distributed without the express
 * permission.
 *******************************************************/
package com.uptake.revenue.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class InvoiceAndRevenueRestWebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean  
    public InternalResourceViewResolver viewResolver() {  
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
        resolver.setPrefix("/");  
        resolver.setSuffix(".html");
        return resolver;  
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/invoices").setViewName("invoices");
		registry.addViewController("/listOfInvoice").setViewName("listOfInvoice");
		registry.addRedirectViewController("/", "/index");
	}

}
