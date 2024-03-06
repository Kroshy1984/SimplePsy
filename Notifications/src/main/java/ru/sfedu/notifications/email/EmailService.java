package ru.sfedu.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
class EmailService {
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
        System.out.println("Sending email to the " + name);
        javaMailSender.send(message);
    }
}