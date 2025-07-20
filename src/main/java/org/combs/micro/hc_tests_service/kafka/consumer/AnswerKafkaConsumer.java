package org.combs.micro.hc_tests_service.kafka.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerKafkaConsumer {
    private final AnswerService answerService;

    @KafkaListener(topics = "answers")
    public void consumeAnswer(AnswerRequest request){
        log.info("Consumed answer by result id: {}", request.getResultId());
        answerService.createAnswer(request);
    }
}
