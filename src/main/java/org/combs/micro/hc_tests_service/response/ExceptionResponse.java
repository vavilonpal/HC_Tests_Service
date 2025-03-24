package org.combs.micro.hc_tests_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String message;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
