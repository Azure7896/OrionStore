package pl.orionproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @GetMapping("/cart/ordercompleted/{id}")
    public String viewCompletedOrderPage(@PathVariable Long id, Model model) {
        model.addAttribute("order", id);
        return "ordercompleted";
    }
}
