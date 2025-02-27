package org.combs.micro.hc_tests_service.configuration;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.results-queue.name}")
    private String resultsQueue;

    @Value("${spring.rabbitmq.results-queue.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.results-queue.routing.key}")
    private String resultsRoutingKey;

    @Bean
    public Queue resultsQueue(){
        return new Queue(resultsQueue, false);
    }

    @Bean
    public TopicExchange resultsExchange(){
        return new TopicExchange(exchange);
    }

    //binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(resultsQueue())
                .to(resultsExchange())
                .with(resultsRoutingKey);
    }
    *//*
    * ConnectionFactory
    * RAbbitTemplate
    * RabbitADmin
    * *//*

}*/
