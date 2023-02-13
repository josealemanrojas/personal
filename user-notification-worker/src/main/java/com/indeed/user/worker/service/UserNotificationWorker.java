package com.indeed.user.worker.service;

import com.indeed.user.worker.model.UserNotification;
import com.indeed.user.worker.repository.UserNotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserNotificationWorker {

    UserNotificationRepository userNotificationRepository;

    @KafkaListener(id = "demoGroup", topics = "user-notification")
    public void consume(ConsumerRecord<String, UserNotification> record) {

        log.info("Received {}", record.value().toString());
        UserNotification userNotification = record.value();
        log.info("Sending email {} to  [{}]", userNotification.getEmail(), userNotification.getUsername());

        userNotificationRepository.save(userNotification);
    }

}
