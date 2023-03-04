package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.datatransferobjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
import pl.orionproject.model.OrderItem;
import pl.orionproject.repository.CategoryRepository;
import pl.orionproject.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;


    public void addItem(ItemDto itemDto) {
        Category category = categoryRepository.findByCategoryName(itemDto.getCategory());
        Item item = new Item(itemDto.getItemName(), itemDto.getImagePath(), itemDto.getQuantity(),
                itemDto.getPrice(), itemDto.getDescription(), category);
        itemRepository.save(item);
    }

    public List<Item> viewAllItemsExceptItemsQuantityEqualZero() {
        return itemRepository.findAll().stream().filter(item -> item
                .getQuantity() > 0).collect(Collectors.toList());
    }

    public List<Item> viewAllItems() {
        return itemRepository.findAll();
    }

    public Item viewItemById(Long id) {
        return itemRepository.findItemById(id);
    }

    public boolean areEnoughItemsInDatabase(List<OrderItem> orderItems) {
        boolean areEnough = true;
        for (OrderItem orderedItem : orderItems) {
            Item item = itemRepository.findItemByItemName(orderedItem.getItemName());
            int itemQuantity = item.getQuantity();
            if (itemQuantity - orderedItem.getQuantity() < 0) {
                areEnough = false;
            }
        }
        return areEnough;
    }

    public void removeAProductsPiecesAfterOrderCompleted(List<OrderItem> orderItems) {
        for (OrderItem orderedItem : orderItems) {
            Item item = itemRepository.findItemByItemName(orderedItem.getItemName());
            int itemQuantity = item.getQuantity() - orderedItem.getQuantity();
            item.setQuantity(itemQuantity);
            itemRepository.save(item);
        }
    }

    public boolean isItemExists(String itemName) {
        return itemRepository.findItemByItemName(itemName) != null;
    }

}
