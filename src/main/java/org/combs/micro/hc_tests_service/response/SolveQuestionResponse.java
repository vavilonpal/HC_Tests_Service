package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SolveQuestionResponse {
    private Long questionId;
    private String description;
    private String schoolSubjectName;
    private Integer difficultly;
}
