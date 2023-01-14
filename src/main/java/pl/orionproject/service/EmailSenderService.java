package pl.orionproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;
import java.util.Date;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Async
    public void sendEmail (String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        Date date = new Date();
        User user = new User("x", "haslo12345", date);
        message.setFrom("orionstoreproject@gmail.com");
        message.setTo(toEmail);
        message.setText(body + " " + user.getEmail());
        message.setSubject(subject + date.getTime());
        mailSender.send(message);
        userRepository.save(user);
        System.out.println("Mail sent succesfully");
    }
}
