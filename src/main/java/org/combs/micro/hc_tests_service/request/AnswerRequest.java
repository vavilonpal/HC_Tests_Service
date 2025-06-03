package org.combs.micro.hc_tests_service.request;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {

    @NotNull
    private Long studentId;
    private Long resultId;
    private Long questionId;
    private Map<String, List<Object>> studentAnswer;

}
