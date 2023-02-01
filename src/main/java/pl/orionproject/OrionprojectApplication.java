package pl.orionproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import pl.orionproject.DataTransferObjects.UserRegistrationDTO;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;
import pl.orionproject.service.EmailSenderService;
import pl.orionproject.service.UserService;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class OrionprojectApplication {
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        /*Date date = new Date();
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Szymon", "Napora", "12345",
                "azurusek@gmail.com", false);
        userService.registerUser(userRegistrationDTO);*/
    }

}