package pl.orionproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.SessionService;
import pl.orionproject.service.UserServiceImpl;


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