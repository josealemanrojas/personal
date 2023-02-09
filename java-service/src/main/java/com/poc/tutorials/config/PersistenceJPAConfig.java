package com.poc.tutorials.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories("com.poc.tutorials")
@EntityScan("com.poc.tutorials")
@Profile("!test")
@EnableTransactionManagement
public class PersistenceJPAConfig {

}
