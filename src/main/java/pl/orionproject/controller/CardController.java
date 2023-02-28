package pl.orionproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.model.Item;
import pl.orionproject.model.User;
import pl.orionproject.repository.ItemRepository;
//import pl.orionproject.repository.ShoppingCartRepository;
import pl.orionproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CardController {

    @Autowired
    ItemRepository itemRepository;

//    @Autowired
//    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/card/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findItemById(id));
        return "card";
    }
//
//    @GetMapping("/card/additem/{id}")
//    public String addItem(@PathVariable Long id, Model model) {
//        User user = userRepository.findByEmail("azurusek@gmail.com");
//        ShoppingCart shoppingCart = user.getShoppingCart();
//        Item item = itemRepository.findItemById(id);
//        List<Item> items = new ArrayList<>();
//        items.add(item);
//        shoppingCart.setItems(items);
//        shoppingCartRepository.save(shoppingCart);
//        return "redirect:/";
//    }

}
