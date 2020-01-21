package com.slimakanzer.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RestAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestAppApplication.class, args);
    }

}
