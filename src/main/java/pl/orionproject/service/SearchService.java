package pl.orionproject.service;

import org.springframework.stereotype.Service;
import pl.orionproject.datatransferobjects.ItemDto;
import pl.orionproject.model.Item;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    public List<Item> searchItems(List<Item> items, ItemDto itemDto) {
        return items.stream().filter(item -> item.getItemName()
                        .contains(itemDto.getItemName()))
                .collect(Collectors.toList());
    }
}
