package pl.orionproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.orionproject.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void registerUser() {
    }

    @Test
    void activateAccount() {
    }

    @Test
    void addUserAdditionalInformation() {
    }

    @Test
    void loadUserByUsername() {
        UserDetails userDetails = userService.loadUserByUsername("azurusek@gmail.com");
        assertEquals("azurusek@gmail.com", userDetails.getUsername());
    }

    @Test
    void getAuthorities() {
        List<Role> roles = Arrays.asList(new Role("ADMIN"), new Role("USER"));
        assertFalse(userService.getAuthorities(roles).isEmpty());
    }
}