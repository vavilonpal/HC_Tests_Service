package org.combs.micro.hc_tests_service.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Converter(autoApply = true)
public class AnswerJsonConverter implements AttributeConverter<Map<String, Object>, PGobject> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(Map<String, Object> attribute) {
        if (attribute == null) return null;
        try {
            PGobject pgObject = new PGobject();
            pgObject.setType("jsonb");
            pgObject.setValue(objectMapper.writeValueAsString(attribute));
            return pgObject;
        } catch (JsonProcessingException | SQLException e) {
            throw new RuntimeException("Ошибка при сериализации JSON в PGobject", e);
        }
    }
    @Override
    public Map<String, Object> convertToEntityAttribute(PGobject pGobject) {
        if (pGobject == null || pGobject.getValue() == null) {
            return null;
        }
        try {
            return objectMapper.readValue(pGobject.getValue(), Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка десериализации JSON из PGobject", e);
        }
    }
}
