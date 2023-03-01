package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.model.Order;
import pl.orionproject.model.OrderItem;
import pl.orionproject.model.ShoppingCartItems;
import pl.orionproject.repository.OrderRepository;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    public List<OrderItem> createOrderItems(Order order) {
        List<ShoppingCartItems> shoppingCartItems = shoppingCartService.fillItemsByUser();
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCartItems sh : shoppingCartItems) {
            orderItems.add(new OrderItem(sh.getItem().getItemName(), sh.getTotalItems(), sh.getTotalPrices(), order));
        }
        return orderItems;
    }

    public Order createOrder() {
        return orderRepository.save(new Order(new Date(), userRepository.findByEmail(userService.getUserName()), "NOWE"));
    }
}
