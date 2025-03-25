package org.combs.micro.hc_tests_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteResponse {
    private final String message;
    private final LocalDateTime deleteTime = LocalDateTime.now();

    public DeleteResponse(String message) {
        this.message = message;
    }
}
