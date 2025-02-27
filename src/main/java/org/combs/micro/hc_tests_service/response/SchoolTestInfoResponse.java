package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolTestInfoResponse {

    private String title;
    private Long teacherId;
    private String type;
    private String schoolSubjectName;
    private String complexity;
    private Integer classLevel;
    private String description;
    private Integer duration;
}
