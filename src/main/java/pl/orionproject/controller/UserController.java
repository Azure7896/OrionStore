package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.UserRegistrationDTO;
import pl.orionproject.service.UserService;

import java.lang.reflect.Array;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "register";
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @PostMapping("/register")
    public String registerAccount(@ModelAttribute("user")UserRegistrationDTO registrationDTO) {
        userService.registerUser(registrationDTO);
        return "redirect:/login";
    }


}


