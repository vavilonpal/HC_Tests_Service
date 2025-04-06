package org.combs.micro.hc_tests_service.service;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.exeptions.AnswerNotFoundException;
import org.combs.micro.hc_tests_service.handler.AnswerPointsHandler;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.repository.AnswerRepository;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;
    private final AnswerPointsHandler pointsHandler;
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id" + id + "not found"));
    }

    public List<Answer> getAnswersByStudentIdAndResultId(Long studentId, Long resultId){
        return answerRepository.findAllByStudentIdAndResultId(studentId,resultId);
    }

    public Answer createAnswer(AnswerRequest request){
        Answer answer = answerMapper.toAnswer(request);
        pointsHandler.defineAnswerCorrectness(answer);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Long answerId, AnswerRequest request) {
        Answer answer = getAnswerById(answerId);
        if (answer.getStudentAnswer().get("answer").equals(request.getStudentAnswer().get("answer"))){
            return answer;
        }
        answer.setStudentAnswer(request.getStudentAnswer());
        pointsHandler.defineAnswerCorrectness(answer);
        return answerRepository.save(answer);
    }
}
