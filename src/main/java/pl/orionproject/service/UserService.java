package pl.orionproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.RoleRepository;
import pl.orionproject.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;
    @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    RoleRepository roleRepository;

    public void registerUser (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<> (roleRepository.findAll()));
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
                + "http://localhost8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
    }
    public User findByUsername (String email) {
        return userRepository.findByEmail(email);
    }


}
