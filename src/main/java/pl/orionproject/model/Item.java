package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;

    private String imagePath;
    private int quantity;

    private double price;

    private String description;
    @ManyToOne
    @JoinColumn(nullable=false)
    private Category category;

    public Item(String itemName, String imagePath, int quantity, double price, String description, Category category) {
        this.itemName = itemName;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
