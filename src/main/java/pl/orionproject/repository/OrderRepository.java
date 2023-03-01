package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.orionproject.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
