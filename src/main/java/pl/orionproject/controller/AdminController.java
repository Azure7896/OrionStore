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
import pl.orionproject.component.CategoryValidator;
import pl.orionproject.component.ItemValidator;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
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

    @Autowired
    ItemValidator itemValidator;

    @Autowired
    CategoryValidator categoryValidator;

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
        model.addAttribute("allitems", itemRepository.findAll());
        return "additem";
    }

    @PostMapping("/admin/additem")
    public String addItem(@ModelAttribute("item") ItemDto itemDto) {
        if (itemValidator.isItemExists(itemDto.getItemName())) {
            return "redirect:/admin/additem?fail";
        } else {
            itemService.addItem(itemDto);
            return "redirect:/";
        }
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
        if (categoryValidator.isCategoryExists(categoryDto.getCategoryName())) {
            return "redirect:/admin/addcategories?fail";
        }
        categoryRepository.save(new Category(categoryDto.getCategoryName()));
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        if (categoryValidator.isCategoryExists(id)) {
            return "redirect:/admin/addcategories?fail";
        }
        categoryRepository.deleteCategoriesByCategoryId(id);
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/category/modify/{id}")
    public String modifyCategory(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findByCategoryId(id);
        model.addAttribute("categorytomodify", category);
        return "categorymodify";
    }

    @PostMapping("/admin/category/modify/{id}") //
    public String modifyCategory(@PathVariable Long id, @ModelAttribute("category") CategoryDto categoryDto) {
        Category category = categoryRepository.findByCategoryId(id);
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/item/modify/{id}")
    public String modifyItem(@PathVariable Long id, Model model) {
        Item item = itemRepository.findItemById(id);
        model.addAttribute("itemtomodify", item);
        return "itemmodify";
    }

    @PostMapping("/admin/item/modify/{id}") //
    public String modifyItem(@PathVariable Long id, @ModelAttribute("item") ItemDto itemDto) {
        Item item = itemRepository.findItemById(id);
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setImagePath(itemDto.getImagePath());
        item.setDescription(itemDto.getDescription());
        item.setQuantity(itemDto.getQuantity());
        item.setCategory(categoryRepository.findByCategoryName(itemDto.getCategory()));
        itemRepository.save(item);
        return "redirect:/admin/additem";
    }
}
