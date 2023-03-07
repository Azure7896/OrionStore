package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public boolean isCategoryExists(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName) != null;
    }

    public boolean isItemWithCategoryExists(Long id) {
        List<Item> items = itemRepository.findAllByCategory(this.getCategoryById(id));
        return  items.size() >= 1;
    }

    public void deleteCategoryFromDatabaseById(Long id) {
        categoryRepository.deleteCategoriesByCategoryId(id);
    }

    public void saveCategoryToDatabase(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findByCategoryId(id);
    }

    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Item> getAllItemsByCategory(Long id) {
        return itemRepository.findAll().stream().filter(item -> item
                .getCategory().getCategoryId() == id && item.getQuantity() > 0).collect(Collectors.toList());
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
