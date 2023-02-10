package com.indeed.user.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfig {

    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public static final String USER_REGISTRATION_EXCHANGE = "userRegistrationExchange";
    public static final String DEAD_LETTER_USER_REGISTRATION_QUEUE = "deadLetter.userRegistration";
    public static final String USER_REGISTRATION_QUEUE = "userRegistration.queue";
    public static final String DEAD_LETTER_ROUTING_KEY = "deadLetter";
    public static final String USER_REGISTRATION_ROUTING_KEY = "userRegistration";

    @Bean
    public DirectExchange deadLetterExchange() {
        log.info("Init user registration dl exchange");
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange exchange() {
        log.info("Init user registration exchange");
        return new DirectExchange(USER_REGISTRATION_EXCHANGE, true, false);
    }

    @Bean
    public Queue userRegistrationDeadLetterQueue() {
        log.info("Init user registration dl queues");
        return  QueueBuilder.durable(DEAD_LETTER_USER_REGISTRATION_QUEUE)
                .build();
    }
    @Bean
    public Queue userRegistrationQueue() {
        log.info("Init user registration  queues");
        return  QueueBuilder.durable(USER_REGISTRATION_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(userRegistrationDeadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(userRegistrationQueue()).to(exchange()).with(USER_REGISTRATION_ROUTING_KEY);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}