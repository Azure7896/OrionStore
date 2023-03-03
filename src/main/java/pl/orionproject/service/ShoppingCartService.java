package pl.orionproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.model.Item;
import pl.orionproject.model.ShoppingCartItems;
import pl.orionproject.model.User;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.repository.ShoppingCartItemsRepository;
import pl.orionproject.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartItemsRepository shoppingCartItemsRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    private double totalALlItemsInTheCart() {
        List<ShoppingCartItems> shoppingCartItems = fillItemsByUser();
        double sum = 0;
        for (ShoppingCartItems li : shoppingCartItems) {
            sum += li.getTotalPrices() * li.getTotalItems();
        }
        return sum;
    }

    public String viewTotalRoundedPrices() {
        return String.format("%.2f", totalALlItemsInTheCart());
    }


//    public void deleteAllUserCartItemsWhenLogout() {
//        shoppingCartItemsRepository.deleteAllShoppingCartItemsByUser(userRepository.findByEmail(userService.getUserName()));
//    }


    public int sumProductsCount() {
        List<ShoppingCartItems> shoppingCartItems = fillItemsByUser();
        int sum = 0;
        for (ShoppingCartItems li : shoppingCartItems) {
            sum += li.getTotalItems();
        }
        return sum;
    }

    @Transactional
    public void deleteAllUserShoppingCartItems() {
        shoppingCartItemsRepository.deleteAllByUser(userRepository.findByEmail(userService.getUserSessionEmail()));
    }

    @Transactional
    public void deleteItemFromCart(Long id) {
        User user = userRepository.findByEmail(userService.getUserSessionEmail());
        shoppingCartItemsRepository.deleteShoppingCartItemsByIdAndUser(id, user);
    }

    public List<ShoppingCartItems> fillItemsByUser() {
        return shoppingCartItemsRepository.findAll().stream().filter(item -> item
                .getUser().getEmail().equals(userService.getUserSessionEmail())).collect(Collectors.toList());
    }

    public void addItemToCart(Long itemId) {
        Item item = itemRepository.findItemById(itemId);

        User user = userRepository.findByEmail(userService.getUserSessionEmail());

        List<ShoppingCartItems> shoppingCartItems = shoppingCartItemsRepository.findAllShoppingCartItemsByItemAndUser(item, user);
        if (shoppingCartItems.size() >= 1) {
            int numberOfItems = shoppingCartItems.get(0).getTotalItems();
            shoppingCartItems.get(0).setTotalItems(++numberOfItems);
            shoppingCartItemsRepository.save(shoppingCartItems.get(0));
        } else {
            shoppingCartItemsRepository.save(new ShoppingCartItems(1, item.getPrice(), user, item));
        }
    }

    public boolean shoppingCartIsEmpty() {
        return fillItemsByUser().size() == 0;
    }

}
