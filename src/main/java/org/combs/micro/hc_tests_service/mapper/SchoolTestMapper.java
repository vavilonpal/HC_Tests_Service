package org.combs.micro.hc_tests_service.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.entity.User;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestInfoResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestSolveInfoResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.combs.micro.hc_tests_service.service.UserService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class SchoolTestMapper {
    private final SchoolSubjectService subjectService;
    private final QuestionMapper questionMapper;
    private final UserService userService;

    public SchoolTest toCreateEntity(SchoolTestRequest request) {
        SchoolSubject schoolSubject = subjectService.getSubjectByName(request.getSchoolSubjectName());
        User teacher = userService.getUserById(request.getTeacherId());
        return SchoolTest.builder()
                .title(request.getTitle())
                .teacher(teacher)
                .type(request.getTestType())
                .schoolSubject(schoolSubject)
                .complexity(request.getTestComplexity())
                .classLevel(request.getClassLevel())
                .description(request.getDescription())
                .duration(request.getDuration())
                .build();
    }


    /**
     * Метод служит дял обноовления существующего теста
     */

    public SchoolTest toUpdateEntity(SchoolTestRequest request, SchoolTest test) {

        if (!(request.getSchoolSubjectName().isEmpty())) {
            SchoolSubject subject = subjectService.getSubjectByName(request.getSchoolSubjectName());
            test.setSchoolSubject(subject);
        }

        test.setTitle(request.getTitle());
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
                .id(test.getId())
                .title(test.getTitle())
                .teacherFullName(test.getTeacher()
                        .getFullName()
                )
                .classLevel(test.getClassLevel())
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
