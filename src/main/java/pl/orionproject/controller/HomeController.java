package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.service.*;

@Controller
public class HomeController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    @Autowired
    ShoppingCartService shoppingCartService;


    @ModelAttribute("searcheditem")
    public ItemDto searchedItem() {
        return new ItemDto();
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("username", userService.createHelloNotification());
        model.addAttribute("email", userService.getUserSessionEmail());
        model.addAttribute("items", itemService.viewAllItems());
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        return "home";
    }

    @PostMapping("/")
    public String viewSearchedItems(Model model, @ModelAttribute("searcheditem") ItemDto item) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("items", searchService.searchItems(itemService.viewAllItems(), item));
        model.addAttribute("email", userService.getUserSessionEmail());
        return "home";
    }

}
