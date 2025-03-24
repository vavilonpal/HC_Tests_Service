package org.combs.micro.hc_tests_service.advice_controller;

import org.combs.micro.hc_tests_service.exeptions.existsException.QuestionByThisDescriptionExistsInThisTest;
import org.combs.micro.hc_tests_service.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExistAdviceController {
    @ExceptionHandler(QuestionByThisDescriptionExistsInThisTest.class)
    public ResponseEntity<ExceptionResponse> handleQuestionExistsException(
            QuestionByThisDescriptionExistsInThisTest exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.ok(response);

    }
}
