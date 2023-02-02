package pl.orionproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;

/*@Entity
@AllArgsConstructor
@NoArgsConstructor*/
public class Item {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    Long id;
    String itemName;
    int quantity;

    double price;

    String description;

}
