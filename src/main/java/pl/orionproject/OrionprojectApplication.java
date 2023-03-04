package pl.orionproject;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.orionproject.controller.UserController;
import pl.orionproject.service.EmailSenderService;
import pl.orionproject.service.SessionService;


@SpringBootApplication
public class OrionprojectApplication {

    @Autowired
    EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() throws MessagingException {
    }

}