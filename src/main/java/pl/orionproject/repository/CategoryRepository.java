package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);

    Category findByCategoryId(Long id);

    Category deleteCategoriesByCategoryId(Long id);
}
