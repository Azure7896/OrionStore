package pl.orionproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.orionproject.repository.UserRepository;
import pl.orionproject.service.EmailSenderService;

@SpringBootApplication
public class OrionprojectApplication {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        senderService.sendEmail("test@test.com",
                "Witaj w sklepie OrionStore!", "Witaj w serwisie");
    }

}
