package org.combs.micro.hc_tests_service.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Converter(autoApply = true)
public class AnswerJsonConverter implements AttributeConverter<Map<String, Object>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        return null;
    }
    @Override
    public Map<String, Object> convertToEntityAttribute(String pGobject) {
        return null;
    }
}
