package com.indeed.user.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserGatewayApplication {

    public static void main(String[] args) {
        System.out.println("starting user registration gateway");
        SpringApplication.run(UserGatewayApplication.class, args);
    }

}
