package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long id;
    private Long teacherId;
    private String schoolSubjectName;
    private String description;
    private String answer;
    private QuestionType type;
    private Integer difficultly;
    private Integer rankPoints;
    private LocalDateTime createdAt;
}
