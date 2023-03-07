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
import pl.orionproject.service.SessionService;
import pl.orionproject.service.ShoppingCartService;

@Transactional
@Controller
public class AdminController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ModelAttribute("item")
    public ItemDto itemDto() {
        return new ItemDto();
    }

    @ModelAttribute("category")
    public CategoryDto categoryDto() {
        return new CategoryDto();
    }

    @GetMapping("/admin")
    public String viewAdminPage() {
        System.out.println(sessionService.getUserSessionEmailNameFromSession());
        return "admin";
    }


    @GetMapping("/admin/additem")
    public String viewAddItemPage(Model model) {
        model.addAttribute("allitems", itemService.getAllItems());
        model.addAttribute("categories", categoryService.getAllCategories());
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
        shoppingCartService.deleteALlShoppingCartItemsById(id);
        itemService.deleteItem(id);
        return "redirect:/admin/additem";
    }

    @GetMapping("/admin/addcategories")
    public String viewAddCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addcategory";
    }

    @PostMapping("/admin/addcategories")
    public String addCategory(@ModelAttribute("category") CategoryDto categoryDto) {
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
    public String viewModifyCategory(@PathVariable Long id, Model model) {
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
    public String viewModifyItem(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("itemtomodify", item);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "itemmodify";
    }

    @PostMapping("/admin/item/modify/{id}") //
    public String modifyItem(@PathVariable Long id, @ModelAttribute("item") ItemDto itemDto) {
        itemService.updateItem(id, itemDto);
        return "redirect:/admin/additem";
    }
}
