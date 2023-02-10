package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Item;
import pl.orionproject.model.Role;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
