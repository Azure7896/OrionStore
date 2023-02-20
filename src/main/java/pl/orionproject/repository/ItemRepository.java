package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.Item;

import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    Item findById(long id);

}
