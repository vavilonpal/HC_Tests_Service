package org.combs.micro.hc_tests_service.advice_controller;


import jakarta.validation.ConstraintViolationException;
import org.combs.micro.hc_tests_service.validation.ValidationErrorResponse;
import org.combs.micro.hc_tests_service.validation.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {


    /**
     * Метод для обработки ошибок валидации внутри тела запроса
     *
     * @param e - ConstraintViolationException - метод ловит это исключение и обрабаывает его получая из его тела все ошибки
     * @return ErrorResponse - который хранит в себе поле в котором была ошибка и сам текст ошибки
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }


    /**
     * Метод ообрабатывает ошибки валидации в параметрах метода
     *
     * @param e MethodArgumentNotValidException - исключение из тела которого мы получаем ошибки валдации.
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

}
