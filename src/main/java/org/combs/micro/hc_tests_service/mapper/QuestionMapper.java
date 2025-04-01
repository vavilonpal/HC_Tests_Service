package org.combs.micro.hc_tests_service.mapper;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.response.SolveQuestionResponse;
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
       // question.setAnswer(request.getAnswer());
        question.setType(request.getType());
        question.setCheckType(request.getCheckType());
        question.setDifficulty(request.getDifficulty());
        question.setRankPoints(request.getRankPoints());
        question.setScorePoints(request.getTestPoints());
    }

    public Question toCreateEntity(QuestionRequest request){
        SchoolSubject schoolSubject = subjectService.getSubjectByName(request.getSchoolSubjectName());

        return Question.builder()
                .teacherId(request.getTeacherId())
                .description(request.getDescription())
                .schoolSubject(schoolSubject)
                .answer(request.getAnswer())
                .type(request.getType())
                .checkType(request.getCheckType())
                .difficulty(request.getDifficulty())
                .rankPoints(request.getRankPoints())
                .scorePoints(request.getTestPoints())
                .build();
    }
    public QuestionResponse toResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .teacherId(question.getTeacherId())
                .testId(question.getTest().getId())
                .schoolSubjectName(question.getSchoolSubject()
                        .getName())
                .description(question.getDescription())
                .answer(question.getAnswer())
                .type(question.getType())
                .difficultly(question.getDifficulty())
                .testPoints(question.getScorePoints())
                .rankPoints(question.getRankPoints())
                .createdAt(question.getCreatedAt())
                .build();
    }
    public SolveQuestionResponse toSolveResponse(Question question){
        return SolveQuestionResponse.builder()
                .questionId(question.getId())
                .description(question.getDescription())
                .schoolSubjectName(question.getSchoolSubject().getName())
                .difficultly(question.getDifficulty())
                .build();
    }
}
