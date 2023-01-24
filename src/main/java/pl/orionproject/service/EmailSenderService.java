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


    @Async
    public void sendEmail (String toEmail, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("orionstoreproject@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
        System.out.println("Mail sent succesfully");
    }
}
