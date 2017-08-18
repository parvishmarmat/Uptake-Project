package com.uptake.revenue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the start point for the application.
 * 
 * @author PM00474968
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.uptake.revenue" })
@EnableAutoConfiguration
public class InvoicAndRevenueRestApiApplication {

	/*@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/InvoiceAndRevenue/*");

        return registrationBean;
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(InvoicAndRevenueRestApiApplication.class, args);
	}
}