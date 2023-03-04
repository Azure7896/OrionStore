package pl.orionproject.datatransferobjects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemName;
    private String imagePath;
    private int quantity;
    private double price;
    private String description;
    private String category;
}


