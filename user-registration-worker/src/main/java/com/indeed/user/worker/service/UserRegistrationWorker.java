package com.indeed.user.worker.service;

import com.indeed.user.worker.model.UserRegistration;
import com.indeed.user.worker.repository.UserRegistrationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.indeed.user.worker.config.RabbitMQConfig.USER_REGISTRATION_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRegistrationWorker {

    UserRegistrationRepository userRegistrationRepository;

    @RabbitListener(queues = {USER_REGISTRATION_QUEUE})
    public void consume(UserRegistration userRegistration) {
        log.info("Received message [{}]", userRegistration);
        if (userRegistrationRepository.existsByEmail(userRegistration.getEmail())) {
            log.error("Email already found {} ", userRegistration.getEmail());
            throw new RuntimeException("Going to deadletter");
        }
        log.info("Received message [{}]", userRegistrationRepository.save(userRegistration));
    }

}
