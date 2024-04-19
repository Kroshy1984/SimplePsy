package ru.sfedu.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }
    @Value("{spring.mail.sender.email}")
    private String senderEmail;

    public void sendTextEmail(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject("Здравствуйте, " + name);
        message.setText("Ваш аккаунт был успешно создан");
        System.out.println("Sending email to " + name);
        javaMailSender.send(message);
    }

    public void sendScoringEmail(List<String> array) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(array.get(2));
        message.setSubject("Здравствуйте, " + array.get(1) + "!");
        message.setText("Пройдите анкету по ссылке: http://localhost:8084/SimplePsyScoring/V1/scoring/" + array.get(0));
        System.out.println("Sending email to " + array.get(1));
        javaMailSender.send(message);
    }
}