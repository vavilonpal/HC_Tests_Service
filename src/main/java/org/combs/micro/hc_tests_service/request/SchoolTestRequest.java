package org.combs.micro.hc_tests_service.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;
import org.springframework.stereotype.Service;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SchoolTestRequest {
    @NotNull(message = "Fill this field")
    private String title;

    @NotNull(message = "Set the teacher id")
    private Long teacherId;

    @NotNull(message = "Set test type")
    private TestType testType;

    @NotNull(message = "Select a school subject of this test")
    private String schoolSubjectName;

    @NotNull(message = "Set complexity")
    private Complexity testComplexity;

    @NotNull(message = "Set a class level of the test")
    private Integer classLevel;

    private String description;

    @Max(value = 90, message = "Test duration has been max 90 minutes length")
    private Integer duration;

}
