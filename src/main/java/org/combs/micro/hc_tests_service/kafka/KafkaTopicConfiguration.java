package org.combs.micro.hc_tests_service.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    /*@Bean
    public KafkaAdmin kafkaAdmin(){
        HashMap<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }*/

    @Bean
    public NewTopic topicAnswer(){
        return TopicBuilder.name("topicAnswer")
                .partitions(3)
                .build();
    }

}
