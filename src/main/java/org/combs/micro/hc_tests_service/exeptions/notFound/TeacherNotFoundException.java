package org.combs.micro.hc_tests_service.exeptions.notFound;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException() {
        super();
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
