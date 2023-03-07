package pl.orionproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.orionproject.datatransferobjects.RestUserInfoDto;
import pl.orionproject.datatransferobjects.UserDto;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserExists(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        return !(user == null);
    }

    public boolean isFieldEmpty(UserDto userDto) {
        return userDto.getEmail().isEmpty() || userDto.getFirstName().isEmpty() ||
                userDto.getLastName().isEmpty() || userDto.getPassword().isEmpty();
    }

    public boolean isNotValidNumberOfCharacters(UserDto userDto) {
        return userDto.getEmail().length() >= 62 || userDto.getEmail().length() <= 6 || userDto.getFirstName().length() >= 50 ||
                userDto.getFirstName().length() <= 2 || userDto.getLastName().length() >= 50 ||
                userDto.getLastName().length() <= 3 || userDto.getPassword().length() >= 255 || userDto.getPassword().length() <= 6;
    }

    public boolean isNotValidRestUserInformation (RestUserInfoDto restUserInfoDto) {
        return restUserInfoDto.getAddress().length() <= 2 || restUserInfoDto.getAddress().length() >= 95 ||
                restUserInfoDto.getPostalCode().length() != 6 || restUserInfoDto.getCity().length() <3 || restUserInfoDto.getCity().length() >= 35 ||
                restUserInfoDto.getPhone().length() != 9;
    }

    public boolean isRestUserInformationFieldEmpty(RestUserInfoDto restUserInfoDto) {
        return restUserInfoDto.getAddress().isEmpty() || restUserInfoDto.getPostalCode().isEmpty() ||
                restUserInfoDto.getCity().isEmpty() || restUserInfoDto.getPhone().isEmpty();
    }
}