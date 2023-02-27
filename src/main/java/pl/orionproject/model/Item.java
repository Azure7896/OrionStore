package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "items")
public class Item implements Comparable <Item> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;

    private String imagePath;
    private int quantity;

    private double price;
    @Column(length = 65555)
    private String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    public Item(String itemName, String imagePath, int quantity, double price, String description, Category category) {
        this.itemName = itemName;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(this.getPrice(), other.getPrice());
    }
}
