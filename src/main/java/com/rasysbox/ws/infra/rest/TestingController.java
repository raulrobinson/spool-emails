package com.rasysbox.ws.infra.rest;

import com.rasysbox.ws.domain.model.EmailResponse;
import com.rasysbox.ws.domain.service.EmailService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@Tag(name = "Testing Email", description = "Testing Email API")
@RequestMapping(path = "${controller.properties.base-path}/testing-emails", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestingController {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestingController.class);

    private final EmailService emailService;

    public TestingController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<EmailResponse> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        try {
            emailService.sendEmail(to, subject, body);
            return new ResponseEntity<>(new EmailResponse(to, "Email queued"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error sending email", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
