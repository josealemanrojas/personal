package com.indeed.user.worker.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories("com.indeed.user.worker")
@EntityScan("com.indeed.user.worker")
@EnableTransactionManagement
public class PersistenceJPAConfig {

}
