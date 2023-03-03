package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.RestUserInfoDto;
import pl.orionproject.model.Order;
import pl.orionproject.model.OrderItem;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.repository.UserRepository;
import pl.orionproject.service.*;
import pl.orionproject.validator.ShoppingCartValidator;

import java.io.IOException;
import java.util.List;

@Controller
public class ShoppingCartController {

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

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @Autowired
    PdfService pdfService;

    @GetMapping("/cart")
    String showCart(Model model) {
        model.addAttribute("cartitems", shoppingCartService.fillItemsByUser());
        model.addAttribute("totalroundedprices", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("userfields", userRepository.findByEmail(userService.getUserSessionEmail()));
        model.addAttribute("restuserinfo", new RestUserInfoDto());

        if (shoppingCartService.shoppingCartIsEmpty()) {
            return "emptycart";
        } else {
            return "shoppingcart";
        }
    }

    @PostMapping("/cart")
    public String orderProducts(@ModelAttribute RestUserInfoDto restUserInfoDto) throws IOException {
        userService.addUserAdditionalInformation(restUserInfoDto);
        Order order = orderService.saveOrder();
        if (itemService.areEnoughItemsInDatabase(orderService.mapOrderItems(order))) {
            List<OrderItem> orderItems = orderService.mapOrderItems(order);
            itemService.removeAProductsPiecesAfterOrderCompleted(orderItems);
            orderService.sendOrderCreatedMessage(order);
            orderService.saveOrderItems(orderItems);
            shoppingCartService.deleteAllUserShoppingCartItems();
            pdfService.createPurchaseInvoice(userService.getUserFromDatabaseBySession(), orderItems);
            return "redirect:/cart/ordercompleted/" + order.getId();
        } else {
            orderService.deleteOrder(order);
            return "redirect:/cart?toomany";
        }
    }

    @GetMapping("/cart/deleteposition/{id}")
    String deletePosition(@PathVariable Long id) {
        shoppingCartService.deleteItemFromCart(id);
        return "redirect:/cart";
    }

//    @PostMapping("/cart/recalculate")
//    String recalculateCart() {
//
//    }
}
