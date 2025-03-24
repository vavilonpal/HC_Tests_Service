package org.combs.micro.hc_tests_service.mapper;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final SchoolSubjectService subjectService;

    public void updateEntityFromRequest(Question question,QuestionRequest request){
        if (!(request.getSchoolSubjectName().isEmpty())){
            SchoolSubject subject = subjectService.getSubjectByName(request.getSchoolSubjectName());
            question.setSchoolSubject(subject);
        }

        question.setDescription(request.getDescription());
        question.setType(request.getType());
        question.setCheckType(request.getCheckType());
        question.setDifficulty(request.getDifficulty());
    }

    public Question toCreateEntity(QuestionRequest request){
        SchoolSubject schoolSubject = subjectService.getSubjectByName(request.getSchoolSubjectName());

        Question question = Question.builder()
                .teacherId(request.getTeacherId())
                .description(request.getDescription())
                .schoolSubject(schoolSubject)
                .answer(request.getAnswer())
                .type(request.getType())
                .checkType(request.getCheckType())
                .difficulty(request.getDifficulty())
                .rankPoints(request.getRankPoints())
                .build();
        return question;
    }
    public QuestionResponse toResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .teacherId(question.getTeacherId())
                .schoolSubjectName(question.getSchoolSubject()
                        .getName())
                .description(question.getDescription())
                .type(question.getType())
                .difficultly(question.getDifficulty())
                .rankPoints(question.getRankPoints())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
