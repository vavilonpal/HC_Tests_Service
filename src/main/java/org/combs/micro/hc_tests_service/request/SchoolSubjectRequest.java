package org.combs.micro.hc_tests_service.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolSubjectRequest {
    private String name;

    public static SchoolSubjectRequest toRequest(SchoolSubject subject){
        return  new SchoolSubjectRequest(subject.getName());
    }
}
