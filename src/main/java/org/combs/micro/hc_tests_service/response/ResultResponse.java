package org.combs.micro.hc_tests_service.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponse {
    private String testTitle;
    private Integer score;
    private Integer rankScore;
    private Integer solveTime;

}
