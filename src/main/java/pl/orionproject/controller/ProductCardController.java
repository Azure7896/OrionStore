package pl.orionproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import pl.orionproject.repository.ShoppingCartRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;


@Controller
public class ProductCardController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

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
