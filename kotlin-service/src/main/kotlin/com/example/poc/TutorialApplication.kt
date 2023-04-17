package com.example.poc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableJpaRepositories("com.example.poc")
@EntityScan("com.example.poc")
@Profile("!test")
@EnableTransactionManagement
class TutorialApplication

fun main(args: Array<String>) {
    SpringApplication.run(TutorialApplication::class.java, *args)
}
