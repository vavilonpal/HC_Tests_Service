package org.combs.micro.hc_tests_service.response;


import lombok.Builder;
import lombok.Data;
import org.combs.micro.hc_tests_service.entity.Question;

import java.util.Set;

@Data
@Builder
public class SchoolTestSolveInfoResponse {
    private String title;
    private String schoolSubjectName;
    private Set<QuestionResponse> questions;
    private Integer duration;

}
