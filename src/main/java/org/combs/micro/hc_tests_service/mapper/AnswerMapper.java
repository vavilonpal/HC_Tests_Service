package org.combs.micro.hc_tests_service.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class AnswerMapper {
    private final QuestionService questionService;
    private final ResultService resultService;

    public Answer toAnswer(AnswerRequest request) {
        Question question = questionService.getQuestionById(request.getQuestionId());
        Result result = resultService.getResultById(request.getResultId());

        return Answer.builder()
                .studentAnswer(request.getStudentAnswer())
                .result(result)
                .question(question)
                .studentId(request.getStudentId())
                .build();
    }

    public AnswerResponse answerToResponse(Answer answer){
        return AnswerResponse.builder()
                .questionDescription(answer.getQuestion()
                        .getDescription())
                .resultId(answer.getResult()
                        .getId())
                .studentId(answer.getStudentId())
                .studentAnswer(answer.getStudentAnswer())
                .rankPoints(answer.getRankPoints())
                .scorePoints(answer.getScorePoints())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build();
    }


}

