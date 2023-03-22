package pl.orionproject.service;

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

    private final CategoryRepository categoryRepository;

    private final ItemRepository itemRepository;

    private final CategoryService categoryService;

    public ItemService(CategoryRepository categoryRepository, ItemRepository itemRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
    }


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

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteItemById(id);
    }


    public void updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findItemById(id);
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setImagePath(itemDto.getImagePath());
        item.setDescription(itemDto.getDescription());
        item.setQuantity(itemDto.getQuantity());
        item.setCategory(categoryService.getCategoryByCategoryName(itemDto.getCategory()));
        itemRepository.save(item);
    }

    public Item getItemById(Long id) {
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

    public void removeFromDatabaseProductsPiecesAfterOrderCompleted(List<OrderItem> orderItems) {
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
