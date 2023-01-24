package pl.orionproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.UserRepository;
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




    public void registerUser (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailSenderService.sendEmail(user.getEmail(), "Witaj w OrionStore, "
                + user.getFirstName() + "!", "Aby potwierdzić swoje konto kliknij w link: "
                + "http://localhost8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
    }
}
