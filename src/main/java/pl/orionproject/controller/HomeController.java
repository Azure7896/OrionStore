package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.DataTransferObjects.UserRegistrationDTO;
import pl.orionproject.repository.ItemRepository;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    ItemRepository itemRepository;

    private List<ItemDto> items = List.of(
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13703K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13703K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13703K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13701K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13702K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor"),
            new ItemDto("Intel Core I7 13703K", "/css/products/intelcorei7.jpg", 60, 2350.99, "Test", "Procesor")
    );
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("items", items);
        return "home";
    }
}
