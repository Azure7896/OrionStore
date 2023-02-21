package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
    Item findItemByCategory(Category category);

}
