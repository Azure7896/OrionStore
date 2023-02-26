package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.SearchService;
import pl.orionproject.service.SessionService;
import pl.orionproject.service.UserService;

@Controller
public class HomeController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    @ModelAttribute("searcheditem")
    public ItemDto searchedItem() {
        return new ItemDto();
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("username", userService.createHelloNotification());
        model.addAttribute("items", itemService.viewAllItems());
        return "home";
    }

    @PostMapping("/")
    public String viewSearchedItems(Model model, @ModelAttribute("searcheditem") ItemDto item) {
        model.addAttribute("items", searchService.searchItems(itemService.viewAllItems(), item));
        return "home";
    }
}
