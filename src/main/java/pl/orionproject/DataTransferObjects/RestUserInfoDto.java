package pl.orionproject.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestUserInfoDto {

    private String vatNumber;
    private String organisationName;
    private String address;
    private String postalCode;
    private String city;
    private String phone;
}
