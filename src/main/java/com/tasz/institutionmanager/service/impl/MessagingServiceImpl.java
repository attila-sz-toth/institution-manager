package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.service.MessagingService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessagingServiceImpl implements MessagingService {

    private JavaMailSender emailSender;

    @Override
    public void sendPassword(String email, String password) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Institution Manager - Új felhasználó");
        message.setText(password);
        emailSender.send(message);
    }
}
