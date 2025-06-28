package org.combs.micro.hc_tests_service.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.exeptions.notFound.AnswerNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.ThisAnswerHasInvalidResultId;
import org.combs.micro.hc_tests_service.handler.AnswerPointsHandler;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.repository.AnswerRepository;
import org.combs.micro.hc_tests_service.repository.cacheRepository.AnswerCacheRepository;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerPointsHandler pointsHandler;
    private final AnswerRepository answerRepository;
    private final AnswerCacheRepository answerCacheRepository;
    private final ObjectMapper objectMapper;

    public Answer getAnswerById(Long id) {
        log.info("Get answer by id: {}", id);
        return answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id" + id + "not found"));

    }

    public List<Answer> getAnswersByStudentIdAndResultId(Long studentId, Long resultId) {
        return answerRepository.findAllByStudentIdAndResultId(studentId, resultId);
    }

    @Transactional
    public Answer createAnswer(AnswerRequest request) throws IOException {
        Answer answer = answerMapper.toAnswer(request);
        pointsHandler.defineAnswerCorrectness(answer);
        log.info(answer.toString());
        return answerRepository.save(answer);
    }

    @Transactional
    public Answer updateAnswer(Long answerId, AnswerRequest request) throws IOException {
        Answer answer = getAnswerById(answerId);
        if (!Objects.equals(answer.getResult().getId(), request.getResultId())) {
            throw new ThisAnswerHasInvalidResultId("Invalid Result id:" + request.getResultId());
        }
        if (answer.getStudentAnswer().get("answer").equals(request.getStudentAnswer().get("answer"))) {
            return answer;
        }
        answer.setStudentAnswer(objectMapper.readTree(request.getStudentAnswer().traverse()));
        pointsHandler.defineAnswerCorrectness(answer);
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
        answerCacheRepository.deleteById(id);
    }

    public Answer updateAnswerByResultIdAndQuestionId(Long resultId, AnswerRequest request) {

        Answer answer = answerRepository.findAnswerByResultIdAndQuestionId(resultId, request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Answer by this result and question not exist"));

        if (answer.getStudentAnswer().get("answer").equals(request.getStudentAnswer().get("answer"))) {
            return answer;
        }
        try {

            answer.setStudentAnswer(objectMapper.readTree(request.getStudentAnswer().traverse()));
            pointsHandler.defineAnswerCorrectness(answer);
            return answerRepository.save(answer);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
