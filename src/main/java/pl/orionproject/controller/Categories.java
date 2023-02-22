package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.model.Item;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;

@Controller
public class Categories {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category/{id}")
    public String registerUserAccount(@ModelAttribute("items") Item item, Model model,
                                      @PathVariable Long id) {
//        model.addAttribute("items", itemRepository.findItemByCategory()
        return "home";
    }

    @GetMapping("/categories")
    public String showCategoriesList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }
}
