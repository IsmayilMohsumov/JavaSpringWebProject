package com.weblogin.demo.service;

import com.weblogin.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
     private JavaMailSender javaMailSender;

     @Autowired
        public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmailWhenUserUpdated(User user) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("imohsumov57@gmail.com");
        mailMessage.setSubject("Updated datas");
        mailMessage.setText("Your account has updated by admin.");
        mailMessage.setText("Try to login again:" + "http://localhost:8081/login");

        javaMailSender.send(mailMessage);
    }
}
