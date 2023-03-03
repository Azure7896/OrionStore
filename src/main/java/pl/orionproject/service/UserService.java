package pl.orionproject.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.orionproject.DataTransferObjects.RestUserInfoDto;
import pl.orionproject.DataTransferObjects.UserDto;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    void registerUser(UserDto registrationDto);
    Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles);

    String createHelloNotification();

    String getUserSessionEmail();
    User getUserFromDatabaseBySession();
    void addUserAdditionalInformation(RestUserInfoDto restUserInfoDto);

    boolean activateAccount(String confirmationToken);


}
