package pl.orionproject.service;

import org.springframework.stereotype.Service;
import pl.orionproject.model.Item;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SortService {
    public List<Item> sortByAsc(List<Item> items) {
        Collections.sort(items);
        return items;
    }
}
