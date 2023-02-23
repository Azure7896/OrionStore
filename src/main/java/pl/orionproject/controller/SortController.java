package pl.orionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.orionproject.model.Item;

@Controller
public class SortController {

    @GetMapping("/sort/desc")
    public String sortByDesc() {
        return "home";
    }

    @GetMapping("/sort/asc")
    public String sortByAsc() {
        return "home";
    }
}
