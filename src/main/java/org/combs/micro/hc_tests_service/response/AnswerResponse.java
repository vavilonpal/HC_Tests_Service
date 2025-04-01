package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.converter.AnswerJsonConverter;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private Map<String, List<Object>> studentAnswer;
    private Long resultId;
    private Long studentId;

    private String questionDescription;

    private Integer rankPoints;
    private Integer scorePoints;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
