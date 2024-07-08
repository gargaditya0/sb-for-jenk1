package com.example.demo;

import com.example.demo.events.UserRegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class LearnSpring1Application {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpring1Application.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRegistrationService userRegistrationService) {
        return arg -> {
            userRegistrationService.register();
            log.info("Exiting init...");
        };
    }

}
