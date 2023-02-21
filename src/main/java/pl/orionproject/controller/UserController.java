package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "register";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDTO() {
        return new UserRegistrationDto();
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (userValidator.isUserExists(registrationDto)) {
            userService.registerUser(registrationDto);
            return "redirect:/register?success";
        } else if (userValidator.isFieldEmpty(registrationDto)) {
            return "redirect:/register?fieldisempty";
        } else if (userValidator.isValidNumberOfCharacters(registrationDto)) {
            return "redirect:/register?wrongnumberofcharacters";
        } else {
            return "redirect:/register?fail";
        }
    }


}


