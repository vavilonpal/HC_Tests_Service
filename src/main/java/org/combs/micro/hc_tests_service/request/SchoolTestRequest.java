package org.combs.micro.hc_tests_service.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private TestType type;

    @NotNull(message = "Select a school subject of this test")
    private SchoolSubject schoolSubject;

    @NotNull(message = "Set complexity")
    private Complexity complexity;

    @NotNull(message = "Set a class level of the test")
    @Size(min = 1, max =  12, message = "Size must has value between 1 and 12")
    private Integer classLevel;

    @Max(value = 255, message = " Too long description")
    private String description;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Max(value = 90, message = "Test duration has been max 90 minutes length")
    private Integer duration;
    @Builder.Default
    private List<Question> questions = new ArrayList<>();


}
