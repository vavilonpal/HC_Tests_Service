package org.combs.micro.hc_tests_service.controller;


import lombok.extern.java.Log;
import org.combs.micro.hc_tests_service.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping("api/v1/publish")
public class MessageController {
    private final RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        log.info("Try to sent message");
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ .....");
    }
}
