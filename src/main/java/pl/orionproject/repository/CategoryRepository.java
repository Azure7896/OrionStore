package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Category;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
    Category findByCategoryName(String categoryName);
}
