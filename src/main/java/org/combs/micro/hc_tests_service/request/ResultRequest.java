package org.combs.micro.hc_tests_service.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultRequest {


    private Long studentId;
    private Integer score;
    private Integer rankScore;


}
