package pl.orionproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.DataTransferObjects.UserDto;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public boolean isUserExists(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        return !(user == null);
    }

    public boolean isFieldEmpty(UserDto userDto) {
        return userDto.getEmail().isEmpty() || userDto.getFirstName().isEmpty()
                || userDto.getLastName().isEmpty() || userDto.getPassword().isEmpty();
    }

    public boolean isNotValidNumberOfCharacters(UserDto userDto) {
        return userDto.getEmail().length() >= 62 || userDto.getFirstName().length() >= 50
                || userDto.getLastName().length() >= 50 || userDto.getPassword().length() >= 255 ||
                userDto.getPassword().length() <= 6;
    }
}
