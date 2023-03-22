package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.ShoppingCartService;
import pl.orionproject.service.SortService;

@Controller
public class SortController {


    private final SortService sortService;


    private final ItemService itemService;


    private final ShoppingCartService shoppingCartService;

    public SortController(SortService sortService, ItemService itemService, ShoppingCartService shoppingCartService) {
        this.sortService = sortService;
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/sort/desc")
    public String sortProductsByDesc(Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("items", sortService.sortItemsByDesc(itemService.viewAllItemsExceptItemsQuantityEqualZero()));
        return "homecategory";
    }

    @GetMapping("/sort/asc")
    public String sortProductsByAsc(Model model) {
        model.addAttribute("count", shoppingCartService.sumProductsCount());
        model.addAttribute("priceofallitems", shoppingCartService.viewTotalRoundedPrices());
        model.addAttribute("items", sortService.sortItemsByAsc(itemService.viewAllItemsExceptItemsQuantityEqualZero()));
        return "homecategory";
    }
}
