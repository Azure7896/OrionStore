package pl.orionproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.orionproject.model.Item;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SortServiceTest {

    @Autowired
    private SortService sortService;

    private final double PRICE = 548.66;

    private List<Item> items = Arrays.asList(new Item ("FirstItem", 2499.99),
            new Item("SecondItem", 1234),
            new Item("ThirdItem", PRICE));

    @Test
    void sortItemsByAsc() {
        assertEquals(PRICE, sortService.sortItemsByAsc(this.items).get(0).getPrice());
    }

    @Test
    void sortItemsByDesc() {
        assertEquals(PRICE, sortService.sortItemsByDesc(this.items).get(2).getPrice());
    }
}