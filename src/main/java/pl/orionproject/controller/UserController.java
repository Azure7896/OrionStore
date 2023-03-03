package pl.orionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.orionproject.DataTransferObjects.UserDto;
import pl.orionproject.validator.UserValidator;
import pl.orionproject.repository.ConfirmationTokenRepository;
import pl.orionproject.service.UserServiceImpl;


@Controller
public class UserController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public String viewLoginPageAfterLogin() {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        if (userService.getUserSessionEmail().equals("anonymousUser")) {
            return "login";
        } else {
            return "redirect:/";
        }
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
    public UserDto userRegistrationDTO() {
        return new UserDto();
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
        if (userValidator.isUserExists(registrationDto) || userValidator.isFieldEmpty(registrationDto)
                || userValidator.isNotValidNumberOfCharacters(registrationDto)) {
            return "redirect:/register?fail";
        } else {
            userService.registerUser(registrationDto);
            return "redirect:/register?success";
        }
    }


    @GetMapping("/confirm-account")
    public String confirmAccount(@RequestParam("token") String confirmationToken) {
        if (userService.activateAccount(confirmationToken)) {
            return "registrationsuccesful";
        } else {
            return "register?fail";
        }
    }
}


