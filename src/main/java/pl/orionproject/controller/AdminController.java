package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
import pl.orionproject.repository.ItemRepository;

@Controller
public class AdminController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/admin")
    public String viewAdminPage() {
        return "home";
    }

    @ModelAttribute("item")
    public ItemDto itemDto() {
        return new ItemDto();
    }

    @GetMapping("/admin/additem")
    public String viewAddItemPage() {
        return "itemadd";
    }

    @PostMapping("/admin/additem")
    public String addItem(@ModelAttribute("item") ItemDto itemDto) {
        itemRepository.save(new Item(itemDto.getItemName(), itemDto.getImagePath(), itemDto.getQuantity(),
                itemDto.getPrice(), itemDto.getDescription(), new Category(itemDto.getCategory())));
        return "redirect:/";
    }
}
