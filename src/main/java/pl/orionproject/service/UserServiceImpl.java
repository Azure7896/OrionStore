package pl.orionproject.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orionproject.datatransferobjects.RestUserInfoDto;
import pl.orionproject.datatransferobjects.UserDto;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.RoleRepository;
import pl.orionproject.repository.UserRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    public UserServiceImpl(EmailSenderService emailSenderService, UserRepository userRepository,
                           ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder, SessionService sessionService) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }

    public void registerUser(UserDto userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getPassword(), userDTO.getEmail(), new Date(),
                false, List.of(new Role("USER")));

        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
                + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
    }

    @Override
    public boolean activateAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            return true;
        } else {
            return false;
        }
    }

    public void addUserAdditionalInformation(RestUserInfoDto restUserInfoDto) {
        User user = getUserFromDatabaseBySession();
        user.setVatNumber(restUserInfoDto.getVatNumber());
        user.setOrganisationName(restUserInfoDto.getOrganisationName());
        user.setAddress(restUserInfoDto.getAddress());
        user.setPostalCode(restUserInfoDto.getPostalCode());
        user.setCity(restUserInfoDto.getCity());
        user.setPhone(restUserInfoDto.getPhone());
        userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null || !user.isActive()) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika" + user.getEmail());
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public String createHelloNotification() {
        if (sessionService.getUserSessionEmailNameFromSession().equals("anonymousUser")) {
            return "Witaj w sklepie OrionStore. Zaloguj się aby otrzymać pełną funkcjonalność.";
        } else {
            return "Witaj w sklepie OrionStore, " + userRepository.findByEmail(sessionService.getUserSessionEmailNameFromSession()).getFirstName()
                    + "! Dziękujemy że jesteś.";
        }
    }

    @Override
    public String getUserSessionEmailName() {
        return sessionService.getUserSessionEmailNameFromSession();
    }

    @Override
    public User getUserFromDatabaseBySession() {
        return userRepository.findByEmail(getUserSessionEmailName());
    }
}
