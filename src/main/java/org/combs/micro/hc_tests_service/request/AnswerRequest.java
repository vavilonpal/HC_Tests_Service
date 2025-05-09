package org.combs.micro.hc_tests_service.request;


import lombok.*;
import org.combs.micro.hc_tests_service.converter.AnswerJsonConverter;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {

    @NotNull
    private Long studentId;
    private Long resultId;
    private Long questionId;
    private Map<String, List<Object>> studentAnswer;

}
