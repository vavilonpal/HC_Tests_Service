package org.combs.micro.hc_tests_service.request;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionCheckType;
import org.combs.micro.hc_tests_service.enums.QuestionType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private Long teacherId;

    @NotNull(message = "Set subject of question")
    private String schoolSubjectName;

    @Size(min = 10,max = 255, message = "Write description of question")
    private String description;

    @NotNull(message = "Set answer for question")
    private JsonNode answer;

    @NotNull(message = "Set type of question")
    private QuestionType type;

    @NotNull(message = "Set  check type of question")
    private Boolean checkType;

    @NotNull(message = "Set value of difficultly")
    private Integer difficulty;
    @Max(value = 10, message = "The size of the rank points should not exceed 10")
    private Integer rankPoints;
    @Max(value = 30, message = "The size of the test points should not exceed 30")
    private Integer testPoints;
}
