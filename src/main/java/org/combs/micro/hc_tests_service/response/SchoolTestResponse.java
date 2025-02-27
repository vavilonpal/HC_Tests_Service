package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolTestResponse {

    private String title;
    private Long teacherId;
    private TestType type;
    private SchoolSubject schoolSubject;
    private Complexity complexity;
    private Integer classLevel;
    private String description;
    private LocalDateTime createdAt;
    private Integer duration;
    private List<Question> questions = new ArrayList<>();



}
