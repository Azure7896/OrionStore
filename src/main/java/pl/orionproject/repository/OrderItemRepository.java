package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}