package org.combs.micro.hc_tests_service.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class ResultResponse {
    private String testTitle;
    private Integer score;
    private Integer rankScore;
    private Integer solveTime;

}
