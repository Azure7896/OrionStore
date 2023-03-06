package pl.orionproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import pl.orionproject.repository.ShoppingCartRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.ShoppingCartService;


@Controller
public class ProductCardController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/productcard/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("item", itemService.viewItemById(id));
        return "productcard";
    }

    @GetMapping("/productcard/additem/{id}")
    public String addItemToCart(@PathVariable Long id, Model model) {
        shoppingCartService.addItemToCart(id);
        return "redirect:/";
    }

}
