package pl.orionproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.repository.ItemRepository;

@Component
public class ItemValidator {

    @Autowired
    ItemRepository itemRepository;

    public boolean isItemExists(String itemName) {
        return itemRepository.findItemByItemName(itemName) != null;
    }

}
