package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.service.ItemService;

@Controller
public class AdminController {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/admin")
    public String viewAdminPage() {
        return "admin";
    }

    @ModelAttribute("item")
    public ItemDto itemDto() {
        return new ItemDto();
    }

    @GetMapping("/admin/additem")
    public String viewAddItemPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "additem";
    }

    @PostMapping("/admin/additem")
    public String addItem(@ModelAttribute("item") ItemDto itemDto) {
        itemService.addItem(itemDto);
        return "redirect:/";
    }

}
