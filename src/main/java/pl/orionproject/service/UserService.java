package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orionproject.DataTransferObjects.UserRegistrationDTO;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.Role;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.UserRepository;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    EmailSenderService emailSenderService;

    UserRegistrationDTO userRegistrationDTO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User user = new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(),
                userRegistrationDTO.getPassword(), userRegistrationDTO.getEmail(), new Date(),
                false, List.of(new Role("USER")));
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        /*emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
                + "http://localhost8080/confirm-account?token=" + confirmationToken.getConfirmationToken());*/
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }


}
