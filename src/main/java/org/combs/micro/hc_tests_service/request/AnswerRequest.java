package org.combs.micro.hc_tests_service.request;

import com.fasterxml.jackson.databind.JsonNode;
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
    
    private JsonNode studentAnswer;


}
