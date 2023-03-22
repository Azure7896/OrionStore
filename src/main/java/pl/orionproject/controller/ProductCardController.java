package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;


@Controller
public class ProductCardController {


    private final ShoppingCartService shoppingCartService;


    private final ItemService itemService;


    private final UserService userService;

    public ProductCardController(ShoppingCartService shoppingCartService, ItemService itemService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("/productcard/{id}")
    public String viewProductCard(@PathVariable Long id, Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("email", userService.getUserSessionEmailName());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("item", itemService.getItemById(id));
        return "productcard";
    }

    @GetMapping("/productcard/additem/{id}")
    public String addItemToShoppingCart(@PathVariable Long id, Model model) {
        shoppingCartService.addItemToCart(id);
        return "redirect:/";
    }

}
