package org.combs.micro.hc_tests_service.consumer;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${spring.rabbitmq.results-queue.name}"})
    public void consume(String message){
        log.info(String.format("Recieved message ->  %s", message));

    }
}
