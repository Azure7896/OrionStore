package pl.orionproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    private CategoryService categoryService;

    @Test
    void isCategoryExists() {
        assertTrue(categoryService.isCategoryExists("Procesor"));
    }

    @Test
    void isItemWithCategoryExists() {
    }

    @Test
    void deleteCategoryFromDatabaseById() {
    }

    @Test
    void saveCategoryToDatabase() {
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void getCategoryByCategoryName() {
    }

    @Test
    void getAllItemsByCategory() {
    }

    @Test
    void getAllCategories() {
    }
}