package org.combs.micro.hc_tests_service.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
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
        SchoolSubject schoolSubject = subjectService.getSubjectByName(request.getSchoolSubjectName());
        Set<Question> questionsOfTest = new HashSet<>();

        if (!(request.getQuestionRequests().isEmpty())) {
            questionsOfTest = request.getQuestionRequests().stream()
                    .map(questionMapper::toCreateEntity)
                    .map(questionService::createQuestion)
                    .collect(Collectors.toSet());
        }

        return SchoolTest.builder()
                .title(request.getTitle())
                .teacherId(request.getTeacherId())
                .type(request.getTestType())
                .schoolSubject(schoolSubject)
                .complexity(request.getTestComplexity())
                .classLevel(request.getClassLevel())
                .description(request.getDescription())
                .duration(request.getDuration())
                .questions(questionsOfTest)
                .build();
    }


    // todo check this method
    public SchoolTest toUpdateEntity(SchoolTestRequest request, SchoolTest test) {
        if (!(request.getSchoolSubjectName().isEmpty())) {
            SchoolSubject subject = subjectService.getSubjectByName(request.getSchoolSubjectName());
            test.setSchoolSubject(subject);
        }

        if (!request.getQuestionRequests().isEmpty()) {

            // Создаём новые вопросы и устанавливаем им тест
            Set<Question> newQuestions = request.getQuestionRequests().stream()
                    .map(requestQuestion -> {
                        Question question = questionMapper.toCreateEntity(requestQuestion);
                        question.setTest(test);// 🚀 Обязательно устанавливаем тест
                        return question;
                    })
                    .collect(Collectors.toSet());

            test.getQuestions().addAll(newQuestions);
        }

        test.setTitle(request.getTitle());
        test.setTeacherId(request.getTeacherId());
        test.setType(request.getTestType());
        test.setComplexity(request.getTestComplexity());
        test.setClassLevel(request.getClassLevel());
        test.setDescription(request.getDescription());
        test.setDuration(request.getDuration());

        return test;
    }

    /**
     * Метод для поулчения лицевой информации теста,
     * при его просмотре, а не решении
     */
    public SchoolTestInfoResponse toInfoResponse(SchoolTest test) {
        return SchoolTestInfoResponse.builder()
                .title(test.getTitle())
                .teacherId(test.getTeacherId())
                .type(test.getType())
                .complexity(test.getComplexity())
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

}
