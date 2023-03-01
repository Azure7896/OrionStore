package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.UserOrderDto;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;

@Controller
public class OrderController {

    @ModelAttribute("user")
    public UserOrderDto userOrderDto() {
        return new UserOrderDto();
    }

    @PostMapping("/cart/order")
    public String orderProducts(@ModelAttribute("user") UserOrderDto userOrderDto) {

    }
}
