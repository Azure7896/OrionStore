package pl.orionproject.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;

import java.util.Collection;
import java.util.Collections;

public interface UserService extends UserDetailsService {
    void registerUser(UserRegistrationDto registrationDto);
    Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles);

    String createHelloNotification();

    String getUserName();
    User getUserFromDatabaseBySession();


}
