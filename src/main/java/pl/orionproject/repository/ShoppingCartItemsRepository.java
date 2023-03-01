package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orionproject.model.Item;
import pl.orionproject.model.ShoppingCartItems;
import pl.orionproject.model.User;

import java.util.List;

public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItems, Long> {
    void deleteAllShoppingCartItemsByUser(User user);

    void deleteShoppingCartItemsByIdAndUser(Long id, User user);

    int countAllByUser(User user);

    List<ShoppingCartItems> findAllShoppingCartItemsByUser(User user);

    List<ShoppingCartItems> findAllShoppingCartItemsByItemAndUser(Item item, User user);

    void deleteAllByUser(User user);
}
