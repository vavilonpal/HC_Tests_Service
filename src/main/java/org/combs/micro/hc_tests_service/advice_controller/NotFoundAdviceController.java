package org.combs.micro.hc_tests_service.advice_controller;


import org.combs.micro.hc_tests_service.exeptions.notFound.QuestionNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.notFound.ResultNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.notFound.SchoolSubjectNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.notFound.SchoolTestNotFoundException;
import org.combs.micro.hc_tests_service.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundAdviceController {

    @ExceptionHandler(SchoolTestNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSchoolNotFoundException(SchoolTestNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleQuestionNotFoundException(QuestionNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResultNotFoundException(ResultNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(SchoolSubjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSchoolSubjectNotFoundException(SchoolSubjectNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        ExceptionResponse response = new ExceptionResponse(exceptionMessage);
        return ResponseEntity.badRequest().body(response);
    }
}
