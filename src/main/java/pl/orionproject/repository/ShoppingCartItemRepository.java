package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Item;
import pl.orionproject.model.ShoppingCartItem;
import pl.orionproject.model.User;

import java.util.List;
@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    void deleteAllShoppingCartItemsByUser(User user);

    void deleteShoppingCartItemsByIdAndUser(Long id, User user);

    int countAllByUser(User user);

    List<ShoppingCartItem> findAllShoppingCartItemsByUser(User user);

    List<ShoppingCartItem> findAllShoppingCartItemsByItemAndUser(Item item, User user);

    void deleteAllByUser(User user);

    void deleteAllByItem(Item item);
}


