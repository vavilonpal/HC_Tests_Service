package org.combs.micro.hc_tests_service.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.combs.micro.hc_tests_service.converter.AnswerJsonConverter;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@Data
public class AnswerRequest {

    private Long studentId;
    private Long resultId;
    private Long questionId;
    private Map<String, List<Object>> studentAnswer;

}
