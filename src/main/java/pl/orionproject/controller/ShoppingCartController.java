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


    @GetMapping("/shoppingcart")
    public String viewShoppingCart(Model model) {
        model.addAttribute("cartitems", shoppingCartService.fillItemsByUser());
        model.addAttribute("totalroundedprices", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("userfields", userService.getUserFromDatabaseBySession());
        model.addAttribute("email", userService.getUserSessionEmailName());
        model.addAttribute("restuserinfo", new RestUserInfoDto());

        if (shoppingCartService.isShoppingCartEmpty()) {
            return "emptycart";
        } else {
            return "shoppingcart";
        }
    }

    @PostMapping("/shoppingcart")
    public String orderProductsFromShoppingCart(@ModelAttribute RestUserInfoDto restUserInfoDto) throws IOException {
        if (userValidator.isRestUserInformationFieldEmpty(restUserInfoDto) || userValidator.isNotValidRestUserInformation(restUserInfoDto)) {
            return "redirect:/shoppingcart?fail";
        } else {
            userService.addUserAdditionalInformation(restUserInfoDto);
            Order order = orderService.saveOrderToDatabase();
            List <OrderItem> orderItems = orderService.mapItemsWithOrderItemsAndOrder(order);
            if (itemService.areEnoughItemsInDatabase(orderItems)) {
                itemService.removeFromDatabaseProductsPiecesAfterOrderCompleted(orderItems);
                orderService.saveOrderItemsToDatabase(orderItems);
                shoppingCartService.deleteAllUserShoppingCartItems();
                String filePath = pdfService.createPurchaseInvoice(userService.getUserFromDatabaseBySession(), orderItems);
                orderService.sendOrderCreatedMail(order, filePath);
                return "redirect:/shoppingcart/ordercompleted/" + order.getId();
            } else {
                orderService.deleteOrder(order);
                return "redirect:/shoppingcart?toomany";
            }
        }
    }

    @GetMapping("/shoppingcart/deleteposition/{id}")
    String deletePositionInShoppingCart(@PathVariable Long id) {
        shoppingCartService.deleteItemFromCart(id);
        return "redirect:/shoppingcart";
    }
}
