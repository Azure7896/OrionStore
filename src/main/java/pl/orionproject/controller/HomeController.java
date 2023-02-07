package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.orionproject.DataTransferObjects.ItemDto;

@Controller
public class HomeController {
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("item", new ItemDto("Intel Core I7 13700K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"));
        return "home";
    }

    @GetMapping("/admin")
    public String viewAdminPage() {
        return "home";
    }

    @GetMapping("/admin/additem")
    public String viewAddItemPage() {
        return "itemadd";
    }

}
