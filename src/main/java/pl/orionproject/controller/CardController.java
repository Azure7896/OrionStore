package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.orionproject.repository.ItemRepository;

@Controller
public class CardController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/card/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findItemById(id));
        return "card";
    }
}
