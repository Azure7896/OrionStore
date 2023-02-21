package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public boolean isUserExists(UserRegistrationDto userRegistrationDto) {
        User user = userRepository.findByEmail(userRegistrationDto.getEmail());
        return user == null;
    }

    public boolean isFieldEmpty(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getEmail().isEmpty() || userRegistrationDto.getFirstName().isEmpty()
                || userRegistrationDto.getLastName().isEmpty() || userRegistrationDto.getPassword().isEmpty();
    }

    public boolean isValidNumberOfCharacters(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getEmail().length() <= 62 || userRegistrationDto.getFirstName().length() <= 50
                || userRegistrationDto.getLastName().length() <= 50 || userRegistrationDto.getPassword().length() <= 255 &&
                userRegistrationDto.getPassword().length() >= 6;
    }
}
