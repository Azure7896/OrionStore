package pl.orionproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.service.OrderService;
import pl.orionproject.service.UserService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart/ordercompleted/{id}")
    public String viewCompletedOrderPage(@PathVariable Long id, Model model) {
        model.addAttribute("order", id);
        return "ordercompleted";
    }

    @GetMapping("/myorders")
    public String viewAllUserOrder(Model model) {
        model.addAttribute("email", userService.getUserSessionEmail());
        model.addAttribute("orders", orderService.getAllOrdersByUser());
        return "myorders";
    }

    @GetMapping("/myorders/order/{id}")
    public String viewSpecificOrder(Model model, @PathVariable Long id) {
        model.addAttribute("email", userService.getUserSessionEmail());
        model.addAttribute("orderitems", orderService.getOrderItemsById(id));
        return "specificorder";
    }
}
