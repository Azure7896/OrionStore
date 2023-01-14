package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

public class UserService {
    //PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public void registerUser (User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
