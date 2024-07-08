package com.example.demo.events;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class UserRegistrationService {

    private ApplicationEventPublisher applicationEventPublisher;

    public UserRegistrationService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register() {
        log.info("Publishing user-registration event");
        applicationEventPublisher.publishEvent(new UserRegistrationEvent(this, "Test-User-" + UUID.randomUUID()));
    }

}
