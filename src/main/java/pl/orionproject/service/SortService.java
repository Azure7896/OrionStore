package pl.orionproject.service;

import org.springframework.stereotype.Service;
import pl.orionproject.model.Item;

import java.util.Collections;
import java.util.List;

@Service
public class SortService {
    public List<Item> sortItemsByAsc(List<Item> items) {
        Collections.sort(items);
        return items;
    }

    public List<Item> sortItemsByDesc(List<Item> items) {
        Collections.sort(items);
        Collections.reverse(items);
        return items;
    }
}
