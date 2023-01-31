package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String viewHomePage() {

        return "home";
    }

    @GetMapping("/admin")
    public String viewAdminPage() {
        return "home";
    }

}
