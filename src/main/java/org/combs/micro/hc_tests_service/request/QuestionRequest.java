package org.combs.micro.hc_tests_service.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionCheckType;
import org.combs.micro.hc_tests_service.enums.QuestionType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    private String answer;
    @NotNull(message = "Set type of question")
    private QuestionType type;

    @NotNull(message = "Set  check type of question")
    private Boolean checkType;
    @NotNull(message = "Set value of difficultly")
    private Integer difficulty;

    private Integer rankPoints;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


}
