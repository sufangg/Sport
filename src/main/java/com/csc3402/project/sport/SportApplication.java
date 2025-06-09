package com.csc3402.project.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportApplication.class, args);
    }
}