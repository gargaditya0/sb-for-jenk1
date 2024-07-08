package com.example.demo.events;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@EnableAsync
public class NotifyUserService {

    @Async
    @EventListener
    public void handleUserRegistrationEvent(UserRegistrationEvent event) {
        log.info("Notifying creation of user {}", event.getUsername());
    }

}
