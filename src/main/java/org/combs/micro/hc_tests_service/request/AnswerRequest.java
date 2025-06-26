package org.combs.micro.hc_tests_service.request;

import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {

    @NotNull
    private Long studentId;
    @NotNull
    private Long resultId;
    @NotNull
    private Long questionId;
    @Builder.Default
    private Map<String, List<Object>> studentAnswer = new HashMap<>() {{
        put("answer", new ArrayList<>());
    }};


    public Map<String, List<Object>> getStudentAnswer() {
        if (studentAnswer == null || !studentAnswer.containsKey("answer")) {
            studentAnswer = new HashMap<>();
            studentAnswer.put("answer", new ArrayList<>());
        }
        return studentAnswer;
    }

}
