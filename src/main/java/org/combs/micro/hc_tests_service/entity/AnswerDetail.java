package org.combs.micro.hc_tests_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.enums.QuestionAnswerType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDetail {
    private QuestionAnswerType questionAnswerType;
    private String trueAnswer;
    private String studentAnswer;
}
