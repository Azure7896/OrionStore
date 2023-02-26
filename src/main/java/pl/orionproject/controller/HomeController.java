package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.SessionService;

@Controller
public class HomeController {
    @Autowired
    ItemService itemService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("items", itemService.viewAllItems());
        return "home";
    }
}
