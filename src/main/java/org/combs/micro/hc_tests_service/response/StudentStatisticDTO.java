package org.combs.micro.hc_tests_service.response;


import lombok.*;

@Data
@AllArgsConstructor
public class StudentStatisticDTO {
    private Integer score = 0;
    private Integer rankScore = 0;
    private Integer testsAmount = 0;
}
