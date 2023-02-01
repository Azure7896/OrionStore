
package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

 User user = userRepository.findByEmail(username);
        if(user==null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika" + user.getEmail());
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()).password(user.getPassword()).authorities("USER").build();

        return null;

    }
}

