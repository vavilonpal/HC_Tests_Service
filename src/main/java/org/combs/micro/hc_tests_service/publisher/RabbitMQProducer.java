package org.combs.micro.hc_tests_service.publisher;


import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class RabbitMQProducer {
    @Value("${spring.rabbitmq.results-queue.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.results-queue.routing.key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        log.info(String.format("Message sent  ->  %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
