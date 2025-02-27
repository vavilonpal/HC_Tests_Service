package org.combs.micro.hc_tests_service.mapper;

import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public void updateEntityFromRequest(Question question,QuestionRequest request){
        question.setSchoolSubject(request.getSchoolSubject());
        question.setDescription(request.getDescription());
        question.setType(request.getType());
        question.setDifficulty(request.getDifficulty());
    }

    public Question toEntity(QuestionRequest request){
        return Question.builder()
                .teacherId(request.getTeacherId())
                .schoolSubject(request.getSchoolSubject())
                .description(request.getDescription())
                .type(request.getType())
                .difficulty(request.getDifficulty())
                .rankPoints(request.getRankPoints())
                .createdAt(request.getCreatedAt())
                .build();
    }
    public QuestionResponse toResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .teacherId(question.getTeacherId())
                .schoolSubject(question.getSchoolSubject())
                .description(question.getDescription())
                .type(question.getType())
                .difficultly(question.getDifficulty())
                .rankPoints(question.getRankPoints())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
