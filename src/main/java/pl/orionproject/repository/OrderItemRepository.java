package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Order;
import pl.orionproject.model.OrderItem;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findALlByOrderId(Long id);
}