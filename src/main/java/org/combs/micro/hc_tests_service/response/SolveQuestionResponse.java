package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class SolveQuestionResponse {
    private Long resultId;
    private Long questionId;
    private String description;
    private String schoolSubjectName;
    private Integer difficultly;
    private List<String> answerOptions;
}
