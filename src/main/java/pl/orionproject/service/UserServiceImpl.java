package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.ItemRepository;
import pl.orionproject.repository.RoleRepository;
import pl.orionproject.repository.UserRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemRepository itemRepository;

    public void registerUser(UserRegistrationDto userRegistrationDTO) {
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User user = new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(),
                userRegistrationDTO.getPassword(), userRegistrationDTO.getEmail(), new Date(),
                false, List.of(new Role("USER"))/*, new ShoppingCart(itemRepository.findAll())*/);
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
                + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null || user.isActive() == false) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika" + user.getEmail());
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public String createHelloNotification() {
        if (sessionService.getUserSessionEmail().equals("anonymousUser")) {
            return "Witaj w sklepie OrionStore. Zaloguj się aby otrzymać pełną funkcjonalność.";
        } else {
            return "Witaj w sklepie OrionStore, " + userRepository.findByEmail(sessionService.getUserSessionEmail()).getFirstName()
                    + "! Dziękujemy że jesteś.";
        }
    }

    public String getUserName() {
        return sessionService.getUserSessionEmail();
    }


}
