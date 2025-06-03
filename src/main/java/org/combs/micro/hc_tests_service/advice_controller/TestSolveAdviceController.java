package org.combs.micro.hc_tests_service.advice_controller;


import org.combs.micro.hc_tests_service.exeptions.AttemptTimeSolveExpireException;
import org.combs.micro.hc_tests_service.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestSolveAdviceController {
    @ExceptionHandler(AttemptTimeSolveExpireException.class)
    public ResponseEntity<ExceptionResponse> handleSchoolNotFoundException(AttemptTimeSolveExpireException exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.badRequest().body(response);
    }
}
