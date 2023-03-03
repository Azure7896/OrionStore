package pl.orionproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.model.Item;
import pl.orionproject.model.ShoppingCartItems;
import pl.orionproject.model.User;
import pl.orionproject.repository.ItemRepository;
//import pl.orionproject.repository.ShoppingCartRepository;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.repository.UserRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CardController {


    @Autowired
    private ShoppingCartItemsRepository shoppingCartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/card/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("item", itemService.viewItemById(id));
        return "card";
    }

    @GetMapping("/card/additem/{id}")
    public String addItemToCart(@PathVariable Long id, Model model) {
        shoppingCartService.addItemToCart(id);
        return "redirect:/";
    }

}
