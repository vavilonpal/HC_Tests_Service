package org.combs.micro.hc_tests_service.exeptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
