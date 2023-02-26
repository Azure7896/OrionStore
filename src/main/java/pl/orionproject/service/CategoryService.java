package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.model.Item;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public List<Item> itemsByCategory(List<Item> items, Long id) {
        return items.stream().filter(item -> item
                .getCategory().getCategoryId() == id && item.getQuantity() > 0).collect(Collectors.toList());
    }
}
