package pl.orionproject.service;

import jakarta.mail.MessagingException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.orionproject.datatransferobjects.RestUserInfoDto;
import pl.orionproject.datatransferobjects.UserDto;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    void registerUser(UserDto registrationDto) throws MessagingException;
    Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles);

    String createHelloNotification();

    String getUserSessionEmail();
    User getUserFromDatabaseBySession();
    void addUserAdditionalInformation(RestUserInfoDto restUserInfoDto);

    boolean activateAccount(String confirmationToken);


}
