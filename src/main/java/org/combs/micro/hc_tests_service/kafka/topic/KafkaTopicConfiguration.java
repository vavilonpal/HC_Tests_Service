package org.combs.micro.hc_tests_service.kafka.topic;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic sendAnswers(){
        return TopicBuilder.name("answers")
                .partitions(3)
                .build();
    }

}
