package com.indeed.user.worker.service;

import com.indeed.user.worker.model.UserRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.indeed.user.worker.config.RabbitMQConfig.QUEUE_NAME;

@Slf4j
@Service
public class UserRegistrationWorker {

    @RabbitListener(queues = {QUEUE_NAME})
    public void consume(UserRegistration userRegistration) {
        log.info("Received message [{}]", userRegistration);
    }

}
