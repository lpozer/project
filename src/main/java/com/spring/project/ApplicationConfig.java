package com.spring.project;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories
public class ApplicationConfig extends AbstractMongoConfiguration{

	@Override
	public MongoClient mongoClient() {
		MongoClient mongo = new MongoClient();
		mongo.setWriteConcern(WriteConcern.SAFE);

		return mongo;
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "project";
	}

}
