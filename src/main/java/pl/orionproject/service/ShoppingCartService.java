package pl.orionproject.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.orionproject.model.Item;
import pl.orionproject.model.ShoppingCartItem;
import pl.orionproject.model.User;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.repository.ShoppingCartItemRepository;
import pl.orionproject.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    public ShoppingCartService(ShoppingCartItemRepository shoppingCartItemRepository, UserService userService,
                               UserRepository userRepository, ItemRepository itemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    private double totalALlItemsFromTheShoppingCart() {
        List<ShoppingCartItem> shoppingCartItems = fillItemsByUser();
        double priceOfAllItems = 0;
        for (ShoppingCartItem li : shoppingCartItems) {
            priceOfAllItems += li.getTotalPrices();
        }
        return priceOfAllItems;
    }

    public String viewTotalRoundedPrices() {
        return String.format("%.2f", this.totalALlItemsFromTheShoppingCart());
    }


    public int sumProductsCount() {
        List<ShoppingCartItem> shoppingCartItems = fillItemsByUser();
        int sum = 0;
        for (ShoppingCartItem li : shoppingCartItems) {
            sum += li.getTotalItems();
        }
        return sum;
    }

    @Transactional
    public void deleteAllUserShoppingCartItems() {
        shoppingCartItemRepository.deleteAllByUser(userRepository.findByEmail(userService.getUserSessionEmailName()));
    }

    public void deleteALlShoppingCartItemsById(Long id) {
        shoppingCartItemRepository.deleteAllByItem(itemRepository.findItemById(id));
    }

    @Transactional
    public void deleteItemFromCart(Long id) {
        User user = userRepository.findByEmail(userService.getUserSessionEmailName());
        shoppingCartItemRepository.deleteShoppingCartItemsByIdAndUser(id, user);
    }

    public List<ShoppingCartItem> fillItemsByUser() {
        return shoppingCartItemRepository.findAll().stream().filter(item -> item
                .getUser().getEmail().equals(userService.getUserSessionEmailName())).collect(Collectors.toList());
    }

    public void addItemToCart(Long itemId) {
        Item item = itemRepository.findItemById(itemId);

        User user = userRepository.findByEmail(userService.getUserSessionEmailName());

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRepository.findAllShoppingCartItemsByItemAndUser(item, user);
        if (shoppingCartItems.size() >= 1) {
            int numberOfItems = shoppingCartItems.get(0).getTotalItems();
            shoppingCartItems.get(0).setTotalItems(++numberOfItems);
            shoppingCartItems.get(0).setTotalPrices(numberOfItems * item.getPrice());
            shoppingCartItemRepository.save(shoppingCartItems.get(0));
        } else {
            shoppingCartItemRepository.save(new ShoppingCartItem(1, item.getPrice(), user, item));
        }
    }

    public boolean isShoppingCartEmpty() {
        return fillItemsByUser().size() == 0;
    }

}
