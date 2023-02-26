package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.orionproject.model.Item;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.service.ItemService;
import pl.orionproject.service.SortService;

@Controller
public class SortController {

    @Autowired
    SortService sortService;

    @Autowired
    ItemService itemService;

    @GetMapping("/sort/desc")
    public String sortByDesc(Model model) {
        model.addAttribute("items", sortService.sortByDesc(itemService.viewAllItems()));
        return "homecategory";
    }

    @GetMapping("/sort/asc")
    public String sortByAsc(Model model) {
        model.addAttribute("items", sortService.sortByAsc(itemService.viewAllItems()));
        return "homecategory";
    }
}
