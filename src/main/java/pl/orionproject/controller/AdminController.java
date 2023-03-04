package pl.orionproject.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.orionproject.datatransferobjects.CategoryDto;
import pl.orionproject.datatransferobjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.service.CategoryService;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.OrderService;
import pl.orionproject.validator.CategoryValidator;

@Transactional
@Controller
public class AdminController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryValidator categoryValidator;
    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingCartItemsRepository shoppingCartItemsRepository;
    @Autowired
    private CategoryService categoryService;

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
        model.addAttribute("categories", categoryService.viewAllCategories());
        return "additem";
    }

    @PostMapping("/admin/additem")
    public String addItem(@ModelAttribute("item") ItemDto itemDto) {
        if (itemService.isItemExists(itemDto.getItemName())) {
            return "redirect:/admin/additem?fail";
        } else {
            itemService.addItem(itemDto);
            return "redirect:/";
        }
    }

    @GetMapping("/admin/item/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        shoppingCartItemsRepository.deleteAllByItem(itemRepository.findItemById(id));
        itemRepository.deleteItemById(id);
        return "redirect:/admin/additem";
    }

    @GetMapping("/admin/addcategories")
    public String viewAddCategories(Model model) {
        model.addAttribute("categories", categoryService.viewAllCategories());
        return "addcategories";
    }

    @PostMapping("/admin/addcategories")
    public String addCategories(@ModelAttribute("category") CategoryDto categoryDto) {
        if (categoryService.isCategoryExists(categoryDto.getCategoryName())) {
            return "redirect:/admin/addcategories?fail";
        }
        categoryService.saveCategoryToDatabase(new Category(categoryDto.getCategoryName()));
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        if (categoryService.isItemWithCategoryExists(id)) {
            return "redirect:/admin/addcategories?fail";
        }
        categoryService.deleteCategoryFromDatabaseById(id);
        return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/category/modify/{id}")
    public String modifyCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("categorytomodify", category);
        return "categorymodify";
    }


    @PostMapping("/admin/category/modify/{id}") //
    public String modifyCategory(@PathVariable Long id, @ModelAttribute("category") CategoryDto categoryDto) {

            Category category = categoryService.getCategoryById(id);
            category.setCategoryName(categoryDto.getCategoryName());
            categoryService.saveCategoryToDatabase(category);
            return "redirect:/admin/addcategories";
    }

    @GetMapping("/admin/item/modify/{id}")
    public String modifyItem(@PathVariable Long id, Model model) {
            Item item = itemRepository.findItemById(id);
            model.addAttribute("itemtomodify", item);
            model.addAttribute("categories", categoryService.viewAllCategories());
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
            item.setCategory(categoryService.getCategoryByCategoryName(itemDto.getCategory()));
            itemRepository.save(item);
            return "redirect:/admin/additem";
    }
}
