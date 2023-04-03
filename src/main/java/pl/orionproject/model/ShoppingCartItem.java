package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shoppingcartitems")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalItems;

    private double totalPrices;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;


    public ShoppingCartItem(int totalItems, double totalPrices, User user, Item item) {
        this.totalItems = totalItems;
        this.totalPrices = totalPrices;
        this.user = user;
        this.item = item;
    }
}
