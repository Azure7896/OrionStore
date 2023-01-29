package pl.orionproject.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.orionproject.model.User;
import pl.orionproject.service.SecurityService;
import pl.orionproject.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/login")
    public String viewLoginPage(String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
        return "strona2";
        }

        if (logout != null) {
            return "strona3";
        }

        return "login";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "register";
    }


}


