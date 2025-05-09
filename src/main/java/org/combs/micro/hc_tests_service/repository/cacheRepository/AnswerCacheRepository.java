package org.combs.micro.hc_tests_service.repository.cacheRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.handler.cacheCalculator.AnswerCacheTtlHandler;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
@Slf4j
@Repository
@RequiredArgsConstructor
public class AnswerCacheRepository {
    private final RedisTemplate<String, AnswerResponse> redisTemplate;
    private final AnswerCacheTtlHandler ttlHandler;
    private final AnswerMapper mapper;
    public void save(Answer answer) {
        Duration answerTtl = ttlHandler.calculateTtl(answer);
        if (answerTtl.isZero()){
            log.info("Time expired answer doesnt may to save in  cache");
            return;
        }
        AnswerResponse responseAnswer = mapper.answerToResponse(answer);
        redisTemplate.opsForValue().set("answer:" + answer.getId(), responseAnswer, answerTtl);
    }

    public AnswerResponse findById(Long id) {
        AnswerResponse cachedAnswerResponse = redisTemplate.opsForValue().get("answer:" + id);
        log.info("Get cached Answer by id: {}", id);
       return cachedAnswerResponse;
    }

    public void deleteById(Long id) {
        redisTemplate.delete("answer:" + id);
    }
}
