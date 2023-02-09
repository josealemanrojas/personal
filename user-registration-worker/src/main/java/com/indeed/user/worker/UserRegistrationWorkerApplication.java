package com.indeed.user.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserRegistrationWorkerApplication {

    public static void main(String[] args) {
        System.out.println("starting user registration gateway");
        SpringApplication.run(UserRegistrationWorkerApplication.class, args);
    }

}
