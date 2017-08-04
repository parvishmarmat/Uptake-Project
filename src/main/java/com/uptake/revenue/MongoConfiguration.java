/*******************************************************
 * Copyright (C) 2015-2016 TechMahindra
 * 
 * This file is part of KafkaAdminAPI-SpringBoot.
 * 
 * KafkaAdminAPI-SpringBoot can not be copied and/or distributed without the express
 * permission.
 *******************************************************/
package com.uptake.revenue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@EnableMongoRepositories
@EnableConfigurationProperties
@ConfigurationProperties(ignoreUnknownFields = true)
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private Integer port;

	@Value("${spring.data.mongodb.username}")
	private String username;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Value("${spring.data.mongodb.password}")
	private String password;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the databaseName
	 */
	@Override
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Constructor
	 */
	public MongoConfiguration() {
		// constructor
	}

	@Override
	@Bean
	public MongoClient mongo() throws Exception {
		return new MongoClient(host, port);
	}

	@Bean
	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		UserCredentials userCred = new UserCredentials(username, password);
		return new MongoTemplate(mongo(), getDatabaseName(), userCred);
	}

	@Override
	public String toString() {
		return "[MongoConfiguration]";
	}

}
