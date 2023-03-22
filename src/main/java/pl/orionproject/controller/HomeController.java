package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.datatransferobjects.ItemDto;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.SearchService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;

@Controller
public class HomeController {

    private final ItemService itemService;


    private final UserService userService;


    private final SearchService searchService;

    private final ShoppingCartService shoppingCartService;

    public HomeController(ItemService itemService, UserService userService, SearchService searchService, ShoppingCartService shoppingCartService) {
        this.itemService = itemService;
        this.userService = userService;
        this.searchService = searchService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("searcheditem")
    public ItemDto searchedItem() {
        return new ItemDto();
    }

    @GetMapping("/")
    public String viewHome(Model model) {
        model.addAttribute("username", userService.createHelloNotification());
        model.addAttribute("email", userService.getUserSessionEmailName());
        model.addAttribute("items", itemService.viewAllItemsExceptItemsQuantityEqualZero());
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        return "home";
    }

    @PostMapping("/")
    public String viewSearchedItems(Model model, @ModelAttribute("searcheditem") ItemDto item) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("items", searchService.searchItems(itemService.viewAllItemsExceptItemsQuantityEqualZero(), item));
        model.addAttribute("email", userService.getUserSessionEmailName());
        return "home";
    }

}
