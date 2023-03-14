package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "items")
public class Item implements Comparable <Item> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String itemName;
    @Column(length = 100)
    private String imagePath;
    @Column(length = 10000)
    private int quantity;
    @Column(length = 10000)
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

    public Item(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(this.getPrice(), other.getPrice());
    }
}
