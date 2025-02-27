package org.combs.micro.hc_tests_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolSubjectResponse {
    private String subjectName;

    public static SchoolSubjectResponse toResponse(SchoolSubject subject){
        return new SchoolSubjectResponse(subject.getName());
    };
}
