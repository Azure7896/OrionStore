package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.userdetails.ReactiveUserDetailsServiceResourceFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.component.ShoppingCartValidator;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.repository.UserRepository;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;

@Controller
public class CartController {

    @Autowired
    ShoppingCartItemsRepository shoppingCartItemsRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartValidator shoppingCartValidator;

    @GetMapping("/cart")
    String showCart(Model model) {
            model.addAttribute("cartitems", shoppingCartService.fillItemsByUser());
            model.addAttribute("priceofallitems", shoppingCartService.viewRoundedPrices());
            model.addAttribute("userfields", userRepository.findByEmail(userService.getUserName()));
            return "cart";
    }

    @GetMapping("/cart/deleteposition/{id}")
    String deletePosition (@PathVariable Long id) {
        shoppingCartService.deleteItemFromCart(id);
        return "redirect:/cart";
    }

//    @PostMapping("/cart/recalculate")
//    String recalculateCart() {
//
//    }
}
