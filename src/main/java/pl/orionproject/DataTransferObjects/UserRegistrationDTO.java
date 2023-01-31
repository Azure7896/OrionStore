package pl.orionproject.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
}
