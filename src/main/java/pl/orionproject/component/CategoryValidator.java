package pl.orionproject.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;

import java.util.List;

@Component
public class CategoryValidator {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    public boolean isCategoryExists(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName) != null;
    }

    public boolean isItemWithCategoryExists(Category category) {
        List<Item> items = itemRepository.findAllByCategory(category);
        return  items.size() >= 1;
    }
}
