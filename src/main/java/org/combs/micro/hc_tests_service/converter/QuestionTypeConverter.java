package org.combs.micro.hc_tests_service.converter;

import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.combs.micro.hc_tests_service.exeptions.QuestionTypeConvertException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class QuestionTypeConverter implements Converter<String, QuestionType> {
    @Override
    public QuestionType convert(String source) {
        try {
            if (source.isEmpty()){
                return null;
            }
            return QuestionType.valueOf(source.toUpperCase());
        }catch (IllegalArgumentException exception){
            throw new QuestionTypeConvertException("Bad question-type syntax");
        }
    }
}
