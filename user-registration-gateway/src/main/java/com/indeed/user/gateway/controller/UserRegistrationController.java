package com.indeed.user.gateway.controller;

import com.indeed.user.gateway.model.UserRegistration;
import com.indeed.user.gateway.model.UserRegistrationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-registration")
@Slf4j
public class UserRegistrationController {

    RabbitTemplate rabbitTemplate;

    /**
     * Creates an user registration.
     */
    @PostMapping
    public ResponseEntity<UUID> createUserRegistration(
            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistration userRegistration = UserRegistration.builder()
                .id(UUID.randomUUID())
                .firstname(userRegistrationRequest.getFirstname())
                .email(userRegistrationRequest.getEmail())
                .lastname(userRegistrationRequest.getLastname())
                .username(userRegistrationRequest.getUsername())
                .build();
        log.info("Received request to create user: {}", userRegistrationRequest);
        rabbitTemplate.convertAndSend("", "q.user-registration", userRegistration);

        return ResponseEntity.ok(userRegistration.getId());
    }


}