package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String viewRegisterPage()
    {
        return "register";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDTO() {
        return new UserRegistrationDto();
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (userService.isUserExists(registrationDto)) {
            userService.registerUser(registrationDto);
            return "redirect:/register?success";
        } else {
            return "redirect:/register?fail";
        }
    }


}


