package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.repository.ItemRepository;


@Controller
public class HomeController {

    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }
}
