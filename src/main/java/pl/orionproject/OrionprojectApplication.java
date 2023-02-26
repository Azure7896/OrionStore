package pl.orionproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.UserServiceImpl;


@SpringBootApplication
public class OrionprojectApplication {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrionprojectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        /*Date date = new Date();
        UserRegistrationDto userRegistrationDTO = new UserRegistrationDto("Szymon", "Napora", "12345",
                "azurusek@gmail.com", false);
        userService.registerUser(userRegistrationDTO);*/
        System.out.println(itemRepository.findById(11L));
    }

}