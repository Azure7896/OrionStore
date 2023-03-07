package pl.orionproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.OrderService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingcart/ordercompleted/{id}")
    public String viewCompletedOrder(@PathVariable Long id, Model model) {
        model.addAttribute("order", id);
        return "ordercompleted";
    }

    @GetMapping("/myorders")
    public String viewAllUserOrders(Model model) {
        model.addAttribute("email", userService.getUserSessionEmailName());
        model.addAttribute("orders", orderService.getAllOrdersByUser());
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        return "myorders";
    }

    @GetMapping("/myorders/order/{id}")
    public String viewSpecifiedOrder(Model model, @PathVariable Long id) {
       model.addAttribute("email", userService.getUserSessionEmailName());
       model.addAttribute("orderitems", orderService.getOrderItemsById(id));
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        return "specifiedorder";
    }
}
