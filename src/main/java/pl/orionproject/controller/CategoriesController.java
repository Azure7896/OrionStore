package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.model.Category;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.CategoryService;

import java.util.stream.Collectors;

@Controller
public class CategoriesController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String registerUserAccount(Model model, @PathVariable Long id) {
        Category category = categoryRepository.findByCategoryId(id);
        if (category==null) {
            return "redirect:/";
        } else {
            model.addAttribute("items", categoryService
                    .itemsByCategory(itemRepository.findAll(), id));
        }
        return "homecategory";
    }

    @GetMapping("/categories")
    public String showCategoriesList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "homecategory";
    }
}
