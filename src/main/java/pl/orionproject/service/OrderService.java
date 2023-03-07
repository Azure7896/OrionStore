package pl.orionproject.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.model.Order;
import pl.orionproject.model.OrderItem;
import pl.orionproject.model.ShoppingCartItems;
import pl.orionproject.repository.OrderItemRepository;
import pl.orionproject.repository.OrderRepository;
import pl.orionproject.repository.UserRepository;

import java.text.SimpleDateFormat;
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

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    EmailSenderService emailSenderService;


    public List<OrderItem> mapItemsWithOrderItemsAndOrder(Order order) {
        List<ShoppingCartItems> shoppingCartItems = shoppingCartService.fillItemsByUser();
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCartItems sh : shoppingCartItems) {
            orderItems.add(new OrderItem(sh.getItem().getItemName(), sh.getTotalItems(), sh.getTotalPrices(), order));
        }
        return orderItems;
    }

    public Order saveOrderToDatabase() {
        return orderRepository.save(new Order(new Date(), userRepository.
                findByEmail(userService.getUserSessionEmailName()), "NOWE"));
    }

    public void deleteOrder(Order order) {
        orderRepository.deleteById(order.getId());
    }

    public void sendOrderCreatedMail(Order order, String invoicePath) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = df.format(new Date());

        emailSenderService.sendEmailWithAttachment(userService.getUserSessionEmailName(),
                "Zarejestrowaliśmy twoje zamówienie nr:" + order.getId() + " - OrionStore",
                "Twoje zamówienie nr: " + order.getId() + " z dnia " + formatted + " zostało zarejestrowane." +
                        " Zostanie zrealizowane tak szybko jak to możliwe." +
                        " Dziękujemy za zakup produktów w OrionStore. Zachęcamy do dalszych zakupów.", invoicePath);
    }


    public List<Order> getAllOrdersByUser () {
        return orderRepository.findAllByUser(userService.getUserFromDatabaseBySession());
    }

    public void saveOrderItemsToDatabase(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }

    public List<OrderItem> getOrderItemsById(Long id) {
        return orderItemRepository.findALlByOrderId(id);
    }

}
