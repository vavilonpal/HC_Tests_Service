package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolTestInfoResponse {

    private String title;
    private Long teacherId;
    private TestType type;
    private String schoolSubjectName;
    private Complexity complexity;
    private Integer classLevel;
    private String description;
    private Integer duration;
}
