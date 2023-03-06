package pl.orionproject.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String toEmail, String subject, String body) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("notificationsorionstore@gmail.com");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
        };
        mailSender.send(messagePreparator);
    }


    @Async
    public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("notificationsorionstore@gmail.com");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);
            mimeMessage.setContent(multipart);
        };
        mailSender.send(messagePreparator);
    }

}
