package com.indeed;

import com.indeed.configuration.AppConfig;
import com.indeed.consumer.ConsumerTask;
import com.indeed.producer.ProducerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@SpringBootApplication
@Slf4j
@Import(AppConfig.class)
public class ConsumerProducerApp implements CommandLineRunner {

    @Autowired
    @Qualifier("consumerTask1")
    ConsumerTask consumerTask1;

    @Autowired
    @Qualifier("consumerTask2")
    ConsumerTask consumerTask2;

    @Autowired
    ProducerTask producerTask;

    @Autowired
    ExecutorService executorService;


    public static void main(String[] args) {
        SpringApplication.run(ConsumerProducerApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Started");

        executorService.execute(producerTask);
        executorService.execute(consumerTask2);
        executorService.execute(consumerTask1);
        executorService.shutdown();
    }
}
