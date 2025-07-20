package org.combs.micro.hc_tests_service.kafka.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerKafkaProducer {
    private final KafkaTemplate<String, AnswerRequest> kafkaTemplate;

    public void sendAnswer(AnswerRequest request){
        log.info("Sending answer by result id : {}", request.getResultId());
        log.info("--------------------------------");
        kafkaTemplate.send("answers", request);
    }

}
