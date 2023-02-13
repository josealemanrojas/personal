package com.indeed.user.worker.service;

import com.indeed.user.worker.model.NotificationType;
import com.indeed.user.worker.model.UserNotification;
import com.indeed.user.worker.model.UserRegistration;
import com.indeed.user.worker.repository.UserRegistrationRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.indeed.user.worker.config.RabbitMQConfig.USER_REGISTRATION_QUEUE;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRegistrationWorker {

    UserRegistrationRepository userRegistrationRepository;
    KafkaTemplate<String, UserNotification> kafkaTemplate;

    public UserRegistrationWorker(UserRegistrationRepository userRegistrationRepository,
                                  KafkaTemplate<String, UserNotification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @RabbitListener(queues = {USER_REGISTRATION_QUEUE})
    public void consume(UserRegistration userRegistration) {
        log.info("Received message [{}]", userRegistration);
        if (userRegistrationRepository.existsByEmail(userRegistration.getEmail())) {
            log.error("Email already found {} ", userRegistration.getEmail());
            throw new RuntimeException("Going to deadletter");
        }
        log.info("Saving message [{}]", userRegistrationRepository.save(userRegistration));

        UserNotification userNotification = UserNotification.builder()
                .id(userRegistration.getId())
                .notificationType(NotificationType.EMAIL)
                .email(userRegistration.getEmail())
                .username(userRegistration.getUsername())
                .build();

        try {
            kafkaTemplate.send("user-notification", userNotification.getUsername(), userNotification);
        } catch (Throwable e) {
            log.error("error {}, {}", e.getMessage(), e.toString());
        }
    }

}
