package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.UserOrderDto;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @ModelAttribute("user")
    public UserOrderDto userOrderDto() {
        return new UserOrderDto();
    }

    @PostMapping("/cart")
    public String orderProducts(@ModelAttribute("user") UserOrderDto userOrderDto) {
        System.out.println(userOrderDto.getVatNumber());
        return "redirect:/";
    }
}
