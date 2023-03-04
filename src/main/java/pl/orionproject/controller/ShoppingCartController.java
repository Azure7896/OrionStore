package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.datatransferobjects.RestUserInfoDto;
import pl.orionproject.model.Order;
import pl.orionproject.model.OrderItem;
import pl.orionproject.service.*;
import pl.orionproject.validator.UserValidator;

import java.io.IOException;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cartitems", shoppingCartService.fillItemsByUser());
        model.addAttribute("totalroundedprices", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("userfields", userService.getUserFromDatabaseBySession());
        model.addAttribute("restuserinfo", new RestUserInfoDto());

        if (shoppingCartService.shoppingCartIsEmpty()) {
            return "emptycart";
        } else {
            return "shoppingcart";
        }
    }

    @PostMapping("/cart")
    public String orderProducts(@ModelAttribute RestUserInfoDto restUserInfoDto) throws IOException {
        if (userValidator.isFieldNotValidRestUserInformationEmpty(restUserInfoDto) || userValidator.isNotValidRestUserInformation(restUserInfoDto)) {
            return "redirect:/cart?fail";
        } else {
            userService.addUserAdditionalInformation(restUserInfoDto);
            Order order = orderService.saveOrder();
            itemService.areEnoughItemsInDatabase(orderService.mapOrderItems(order));
            if (itemService.areEnoughItemsInDatabase(orderService.mapOrderItems(order))) {
                List<OrderItem> orderItems = orderService.mapOrderItems(order);
                itemService.removeAProductsPiecesAfterOrderCompleted(orderItems);
                orderService.sendOrderCreatedMail(order);
                orderService.saveOrderItems(orderItems);
                shoppingCartService.deleteAllUserShoppingCartItems();
                pdfService.createPurchaseInvoice(userService.getUserFromDatabaseBySession(), orderItems);
                return "redirect:/cart/ordercompleted/" + order.getId();
            } else {
                orderService.deleteOrder(order);
                return "redirect:/cart?toomany";
            }
        }
    }

    @GetMapping("/cart/deleteposition/{id}")
    String deletePosition(@PathVariable Long id) {
        shoppingCartService.deleteItemFromCart(id);
        return "redirect:/cart";
    }
}
