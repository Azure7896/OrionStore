package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.orionproject.DataTransferObjects.UserRegistrationDto;
import pl.orionproject.model.ConfirmationToken;
import pl.orionproject.model.User;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.repository.UserRepository;
import pl.orionproject.service.UserServiceImpl;


@Controller
public class UserController {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public String viewLoginPageAfterLogin() {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "register";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDTO() {
        return new UserRegistrationDto();
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (userValidator.isUserExists(registrationDto)) {
            try {
                userServiceImpl.registerUser(registrationDto);
            } catch (Exception e) {
                return e.getMessage();
            }
            return "redirect:/register?success";
        } else if (userValidator.isFieldEmpty(registrationDto)) {
            return "redirect:/register?fieldisempty";
        } else if (userValidator.isValidNumberOfCharacters(registrationDto)) {
            return "redirect:/register?wrongnumberofcharacters";
        } else {
            return "redirect:/register?fail";
        }
    }


    @GetMapping("/confirm-account")
    public String confirmAccount(@RequestParam("token") String confirmToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmToken);
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            return "registrationsuccesful";
        } else {
            return "registerfail";
        }

    }
}


