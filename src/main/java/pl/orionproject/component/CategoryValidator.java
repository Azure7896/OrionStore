package pl.orionproject.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;
@Component
public class CategoryValidator {
    @Autowired
    CategoryRepository categoryRepository;

    public boolean isCategoryExists(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName) != null;
    }

    public boolean isCategoryExists(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId) != null;
    }
}
