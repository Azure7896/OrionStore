package pl.orionproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.orionproject.service.SessionService;


@SpringBootApplication
public class OrionprojectApplication {


    SessionService sessionService;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
    }

}