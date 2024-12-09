package com.rasysbox.ws.domain.service;

import com.rasysbox.ws.domain.model.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class EmailSenderService {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmailSenderService.class);

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(emailRequest.getTo());
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getBody());
            mailSender.send(message);
            log.info("Email sent to {}", emailRequest.getTo());
        } catch (Exception e) {
            log.error("Error sending email to {} {}", emailRequest.getTo(), e.getMessage());
        }
    }
}
