package com.stayen.casa.propertyservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/*
 * MongoDB Configuration for Property Service.
 * Configures MongoClient with custom TimeOut Settings
 */

@Configuration
public class MongoConfig {
	
	@Value("${MONGO_URI}")
	private String mongoUri;
	
	@Value("${MONGO_DB_NAME}")
	private String dbName;
	
	
	/**
     * Configures and creates a MongoClient bean with custom socket and cluster settings.
     * Includes connection timeout, read timeout, and server selection timeout.
     *
     * @return a configured MongoClient instance
     */
	@Bean
	public MongoClient mongoClient()
	{
		ConnectionString connectionString = new ConnectionString(mongoUri);

		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();
		return MongoClients.create(settings);
	}
	
	
	/**
     * Creates a MongoDatabaseFactory using the configured MongoClient and database name.
     * This is required by Spring Data MongoDB to initialize MongoTemplate.
     *
     * @return a MongoDatabaseFactory instance
     */
	@Bean
	public MongoDatabaseFactory mongoDbFactory()
	{
		return new SimpleMongoClientDatabaseFactory(mongoClient(),dbName);
	}
	
	
	/**
     * Provides a MongoTemplate bean for interacting with MongoDB.
     * It is used by Spring Data MongoDB to perform database operations.
     *
     * @return a MongoTemplate instance
     */
	@Bean
	public MongoTemplate mongoTemplate()
	{
		return new MongoTemplate(mongoDbFactory());
	}

}

