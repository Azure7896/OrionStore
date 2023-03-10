package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Order;
import pl.orionproject.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteOrderById(Long id);

    List<Order> findAllByUser(User user);
}
