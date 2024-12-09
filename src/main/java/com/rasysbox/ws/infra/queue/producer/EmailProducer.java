package com.rasysbox.ws.infra.queue.producer;

import com.rasysbox.ws.config.RabbitMQConfig;
import com.rasysbox.ws.domain.model.EmailRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class EmailProducer {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmailProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean sendEmailToQueue(EmailRequest emailRequest) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, emailRequest);
            log.info("Email queued: {}", emailRequest);
            return true;
        } catch (Exception e) {
            log.error("Error sending email to queue: {}", e.getMessage());
        }
        log.error("Error sending email to queue");
        return false;
    }
}
