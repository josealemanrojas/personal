package com.indeed.configuration;

import com.indeed.consumer.ConsumerTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Configuration
@ComponentScan("com.indeed")
public class AppConfig {
    @Bean
    public BlockingQueue<Integer> blockingQueue(){
        return new LinkedBlockingDeque<>();
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(3);
    }

    @Bean("consumerTask1")
    public ConsumerTask consumerTask1(BlockingQueue<Integer> blockingQueue) {
        return new ConsumerTask(blockingQueue);
    }

    @Bean("consumerTask2")
    public ConsumerTask consumerTask2(BlockingQueue<Integer> blockingQueue) {
        return new ConsumerTask(blockingQueue);
    }
}
