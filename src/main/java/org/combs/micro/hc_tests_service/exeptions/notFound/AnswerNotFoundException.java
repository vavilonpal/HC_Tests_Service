package org.combs.micro.hc_tests_service.exeptions.notFound;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException() {
        super();
    }

    public AnswerNotFoundException(String message) {
        super(message);
    }

    public AnswerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
