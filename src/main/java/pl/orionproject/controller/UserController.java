package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import pl.orionproject.service.UserService;

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


}


