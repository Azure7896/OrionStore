package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.model.Category;
import pl.orionproject.service.CategoryService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.UserService;


@Controller
public class CategoriesController {


    private final CategoryService categoryService;


    private final UserService userService;


    private final ShoppingCartService shoppingCartService;

    public CategoriesController(CategoryService categoryService, UserService userService, ShoppingCartService shoppingCartService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/category/{id}")
    public String viewItemsByCategory(Model model, @PathVariable Long id) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("email", userService.getUserSessionEmailName());
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/";
        } else {
            model.addAttribute("items", categoryService
                    .getAllItemsByCategory(id));
        }
        return "homecategory";
    }


    @GetMapping("/categories")
    public String viewCategoriesList(Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("email", userService.getUserSessionEmailName());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        return "categories";
    }

}
