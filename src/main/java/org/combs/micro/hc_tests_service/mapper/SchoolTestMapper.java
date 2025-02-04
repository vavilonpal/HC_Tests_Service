package org.combs.micro.hc_tests_service.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class SchoolTestMapper {
    public SchoolTest toEntity(SchoolTestRequest request){
        return  SchoolTest.builder()
                .title(request.getTitle())
                .teacherId(request.getTeacherId())
                .type(request.getType())
                .schoolSubject(request.getSchoolSubject())
                .complexity(request.getComplexity())
                .classLevel(request.getClassLevel())
                .description(request.getDescription())
                .duration(request.getDuration())
                .questions(request.getQuestions())
                .createdAt(request.getCreatedAt())
                .build();
    }

    public SchoolTestResponse toResponse(SchoolTest test){
        return SchoolTestResponse.builder()
                .title(test.getTitle())
                .teacherId(test.getTeacherId())
                .type(test.getType())
                .schoolSubject(test.getSchoolSubject())
                .complexity(test.getComplexity())
                .classLevel(test.getClassLevel())
                .description(test.getDescription())
                .duration(test.getDuration())
                .questions(test.getQuestions())
                .createdAt(test.getCreatedAt())
                .build();
    }

    public void updateEntityFromRequest(SchoolTestRequest request, SchoolTest test){
        test.setTitle(request.getTitle());
        test.setTeacherId(request.getTeacherId());
        test.setType(request.getType());
        test.setSchoolSubject(request.getSchoolSubject());
        test.setComplexity(request.getComplexity());
        test.setClassLevel(request.getClassLevel());
        test.setDescription(request.getDescription());
        test.setDuration(request.getDuration());
        test.setQuestions(request.getQuestions());

    }


}
