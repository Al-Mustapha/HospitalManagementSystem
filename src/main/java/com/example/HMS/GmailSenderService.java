package com.example.HMS;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GmailSenderService {


    private final JavaMailSender javaMailSender;


    public void sendMail(String toEMail, String toSubject, String body) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        SimpleMailMessage message = new SimpleMailMessage();
        mimeMessageHelper.setFrom("m@gmail.com");
        mimeMessageHelper.setSubject(toSubject);
        mimeMessageHelper.setTo(toEMail);
        mimeMessageHelper.setText(body, true);
        javaMailSender.send(mimeMessage);
    }
}
