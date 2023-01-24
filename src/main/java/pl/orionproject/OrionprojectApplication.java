package pl.orionproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.orionproject.model.User;
import pl.orionproject.service.EmailSenderService;
import pl.orionproject.service.UserService;

import java.util.Date;


@SpringBootApplication
public class OrionprojectApplication {
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        //Date date = new Date();
        //User user = new User("Szymon", "Napora", "12345", "azurusek@gmail.com", date);
        //userService.registerUser(user);
    }

}