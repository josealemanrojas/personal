package com.poc.tutorials.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.poc.tutorials")
public class MongoConfig {

}
