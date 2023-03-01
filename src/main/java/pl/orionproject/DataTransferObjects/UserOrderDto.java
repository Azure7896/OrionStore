package pl.orionproject.DataTransferObjects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDto {


    private int vatNumber;

    private String organisationName;
    private String address;
    private String postalCode;
    private String city;
    private int phone;


}
