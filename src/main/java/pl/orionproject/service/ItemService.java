package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.model.Category;
import pl.orionproject.model.Item;
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

    public List<Item> viewAllItems() {
        return itemRepository.findAll().stream().filter(item -> item
                .getQuantity() > 0).collect(Collectors.toList());
    }
}
