package org.combs.micro.hc_tests_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cache.redis")
@RequiredArgsConstructor
public class RedisConfiguration {

    private final Duration defaultTimeToLive = Duration.ofMinutes(1);
    private final ObjectMapper objectMapper;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(defaultTimeToLive)
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper))
                );

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(configuration)
                .build();
    }

    @Bean
    public RedisTemplate<String, AnswerResponse> answerRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, AnswerResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);


        Jackson2JsonRedisSerializer<AnswerResponse> serializer = new Jackson2JsonRedisSerializer<>(AnswerResponse.class);
        serializer.setObjectMapper(objectMapper);
        template.setValueSerializer(serializer);

        return template;
    }
}
