package com.rasysbox.ws.infra.queue.consumer;

import com.rasysbox.ws.config.RabbitMQConfig;
import com.rasysbox.ws.domain.model.EmailRequest;
import com.rasysbox.ws.domain.service.EmailSenderService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class EmailConsumer {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmailConsumer.class);

    private final EmailSenderService emailSenderService;

    public EmailConsumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeEmail(EmailRequest emailRequest) {
        try {
            emailSenderService.sendEmail(emailRequest);
            log.info("Email sent successfully: {}", emailRequest);
        } catch (Exception e) {
            log.error("Error consuming email: {}", emailRequest, e);
        }
    }
}
