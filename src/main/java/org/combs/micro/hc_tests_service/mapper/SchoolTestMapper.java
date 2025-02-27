package org.combs.micro.hc_tests_service.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestInfoResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestSolveInfoResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class SchoolTestMapper {
    private final SchoolSubjectService subjectService;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public SchoolTest toCreateEntity(SchoolTestRequest request) {
        TestType testType = TestType.valueOf(request.getTestType().toUpperCase());
        Complexity complexity = Complexity.valueOf(request.getTestComplexity().toUpperCase());
        SchoolSubject schoolSubject = subjectService.getSubjectByName(request.getSchoolSubjectName());
        Set<Question> questionsOfTest = new HashSet<>();

        if ((!request.getQuestionRequests().isEmpty())) {
            questionsOfTest = request.getQuestionRequests().stream()
                    .map(questionMapper::toEntity)
                    .map(questionService::createQuestion)
                    .collect(Collectors.toSet());
        }

        return SchoolTest.builder()
                .title(request.getTitle())
                .teacherId(request.getTeacherId())
                .type(testType)
                .schoolSubject(schoolSubject)
                .complexity(complexity)
                .classLevel(request.getClassLevel())
                .description(request.getDescription())
                .duration(request.getDuration())
                .questions(questionsOfTest)
                .build();
    }

    // todo протестить будут ли сохрантнься вопросы
    public SchoolTest toUpdateEntity(SchoolTest test, SchoolTestRequest request) {
        TestType testType = TestType.valueOf(request.getTestType().toUpperCase());
        Complexity complexity = Complexity.valueOf(request.getTestComplexity().toUpperCase());


        if (!(request.getSchoolSubjectName().isEmpty())) {
            SchoolSubject subject = subjectService.getSubjectByName(request.getSchoolSubjectName());
            test.setSchoolSubject(subject);
        }
        if ((!request.getQuestionRequests().isEmpty())) {
            Set<Question> questionsOfTest = request.getQuestionRequests().stream()
                    .map(questionMapper::toEntity)
                    .map(questionService::createQuestion)
                    .collect(Collectors.toSet());
            test.setQuestions(questionsOfTest);
        }
        test.setTitle(request.getTitle());
        test.setTeacherId(request.getTeacherId());
        test.setType(testType);
        test.setComplexity(complexity);
        test.setClassLevel(request.getClassLevel());
        test.setDescription(request.getDescription());
        test.setDuration(request.getDuration());

        return null;
    }

    /**
     * Метод для поулчения лицевой информации теста,
     * при его просмотре, а не решении
     */
    public SchoolTestInfoResponse toInfoResponse(SchoolTest test) {
        return SchoolTestInfoResponse.builder()
                .title(test.getTitle())
                .teacherId(test.getTeacherId())
                .type(test.getType().toString())
                .schoolSubjectName(test.getSchoolSubject()
                        .getName())
                .description(test.getDescription())
                .duration(test.getDuration())
                .build();
    }

    /**
     * Используется для поулчения вопросов теста
     * при старе прохождкния теста
     */
    public SchoolTestSolveInfoResponse toSolveResponse(SchoolTest test) {
        Set<QuestionResponse> questionsOfTest = test.getQuestions().stream()
                .map(questionMapper::toResponse)
                .collect(Collectors.toSet());

        return SchoolTestSolveInfoResponse.builder()
                .title(test.getTitle())
                .schoolSubjectName(test.getSchoolSubject()
                        .getName())
                .questions(questionsOfTest)
                .duration(test.getDuration())
                .build();
    }

    public void updateEntityFromRequest(SchoolTestRequest request, SchoolTest test) {


    }


}
