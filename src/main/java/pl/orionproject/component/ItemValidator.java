package pl.orionproject.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.DataTransferObjects.ItemDto;
import pl.orionproject.model.Item;
import pl.orionproject.repository.ItemRepository;

@Component
public class ItemValidator {

    @Autowired
    ItemRepository itemRepository;

    public boolean isItemExists(String itemName) {
        return itemRepository.findItemByItemName(itemName) != null;
    }

}
