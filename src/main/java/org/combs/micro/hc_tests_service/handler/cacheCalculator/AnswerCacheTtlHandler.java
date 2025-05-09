package org.combs.micro.hc_tests_service.handler.cacheCalculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class AnswerCacheTtlHandler implements CacheTtlCalculator<Answer> {
    private final ResultService resultService;


    @Override
    public Duration calculateTtl(Answer answer) {
        LocalDateTime answerPostTime = answer.getUpdatedAt() == null
                ? answer.getCreatedAt()
                : answer.getUpdatedAt();

        LocalDateTime resultStartTime = answer.getResult().getStartedAt();

        Duration testDuration = Duration.ofMinutes(answer.getResult()
                .getSchoolTest()
                .getDuration());

        LocalDateTime endTime = resultStartTime.plus(testDuration);

        Duration ttl = Duration.between(answerPostTime, endTime);

        if (!ttl.isNegative() && !ttl.isZero()) {
            log.info("TTl is set for answer by id:{} and ttl:{}",answer.getId(), ttl);
            return ttl;
        } else {
            log.info("TTl is not set for answer by id:{}, cause ttl is 0", answer.getId());
            return Duration.ofMinutes(0);
        }
    }
}

