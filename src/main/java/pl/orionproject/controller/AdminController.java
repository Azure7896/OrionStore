package pl.orionproject.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.DataTransferObjects.CategoryDto;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.ItemService;

@Controller
@Transactional
public class AdminController {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/admin")
    public String viewAdminPage() {
        return "admin";
    }

    @ModelAttribute("item")
    public ItemDto itemDto() {
        return new ItemDto();
    }

    @ModelAttribute("category")
    public CategoryDto categoryDto() {
        return new CategoryDto();
    }

    @GetMapping("/admin/additem")
    public String viewAddItemPage(Model model) {
//        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("allitems", itemRepository.findAll());
        return "additem";
    }

    @PostMapping("/admin/additem")
    public String addItem(@ModelAttribute("item") ItemDto itemDto) {
        itemService.addItem(itemDto);
        return "redirect:/";
    }

    @GetMapping("/admin/item/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteItemById(id);
        return "redirect:/admin/additem";
    }

    @GetMapping("/admin/addcategories")
    public String addCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addcategories";
    }

    @PostMapping("/admin/addcategories")
    public String addCategories(@ModelAttribute("category") CategoryDto categoryDto) {
        categoryRepository.save(new Category(categoryDto.getCategoryName()));
        return "redirect:/addcategories";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteCategoriesByCategoryId(id);
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/category/modify/{id}")
    public String modifyCategory (@PathVariable Long id, Model model) {
        Category category = categoryRepository.findByCategoryId(id);
        model.addAttribute("categorytomodify", category);
        return "categorymodify";
    }

    @PostMapping("/admin/category/modify/{id}")
    public String modifyCategory (@PathVariable Long id, @ModelAttribute("category") CategoryDto categoryDto) {
        Category category = categoryRepository.findByCategoryId(id);
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
        return "redirect:/admin/addcategories";
    }
}
