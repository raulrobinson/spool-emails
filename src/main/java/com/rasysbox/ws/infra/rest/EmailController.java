package com.rasysbox.ws.infra.rest;

import com.rasysbox.ws.domain.model.EmailRequest;
import com.rasysbox.ws.domain.model.EmailResponse;
import com.rasysbox.ws.infra.queue.producer.EmailProducer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

@RestController
@Tag(name = "Email", description = "Email API")
@RequestMapping(path = "${controller.properties.base-path}/emails", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmailController.class);

    private final EmailProducer emailProducer;

    public EmailController(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

    @PostMapping
    @Operation(summary = "Queue email", description = "Queue email")
    public ResponseEntity<EmailResponse> queueEmail(@RequestBody EmailRequest emailRequest) {
        boolean response = emailProducer.sendEmailToQueue(emailRequest);
        if (!response) {
            log.error("Failed to queue email: {}", emailRequest);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Email queued: {}", emailRequest.getTo());
        return new ResponseEntity<>(new EmailResponse("queued", "Email queued"), HttpStatus.OK);
    }
}
