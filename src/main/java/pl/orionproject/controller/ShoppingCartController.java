package pl.orionproject.controller;

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

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
public class ShoppingCartController {


    private final ShoppingCartService shoppingCartService;


    private final UserService userService;


    private final OrderService orderService;


    private final ItemService itemService;


    private final PdfService pdfService;


    private final UserValidator userValidator;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, OrderService orderService,
                                  ItemService itemService, PdfService pdfService, UserValidator userValidator) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.pdfService = pdfService;
        this.userValidator = userValidator;
    }

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
    public String orderProductsFromShoppingCart(@ModelAttribute RestUserInfoDto restUserInfoDto) {
        if (userValidator.isRestUserInformationFieldEmpty(restUserInfoDto) || userValidator.isNotValidRestUserInformation(restUserInfoDto)) {
            return "redirect:/shoppingcart?fail";
        } else {
            userService.addUserAdditionalInformation(restUserInfoDto);
            Order order = orderService.saveOrderToDatabase();
            List<OrderItem> orderItems = orderService.mapItemsWithOrderItemsAndOrder(order);
            if (itemService.areEnoughItemsInDatabase(orderItems)) {
                itemService.removeFromDatabaseProductsPiecesAfterOrderCompleted(orderItems);
                orderService.saveOrderItemsToDatabase(orderItems);
                shoppingCartService.deleteAllUserShoppingCartItems();
                String filePath;
                try {
                    filePath = pdfService.createPurchaseInvoice(userService.getUserFromDatabaseBySession(), orderItems);
                } catch (MalformedURLException | FileNotFoundException e) {
                    throw new RuntimeException();
                }
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
