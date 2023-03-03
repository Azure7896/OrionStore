package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Item> itemsByCategory(Long id) {
        return itemRepository.findAll().stream().filter(item -> item
                .getCategory().getCategoryId() == id && item.getQuantity() > 0).collect(Collectors.toList());
    }

    public List<Category> viewAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
            return categoryRepository.findByCategoryId(id);
    }
}
