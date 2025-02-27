package org.combs.micro.hc_tests_service.exeptions.existsException;

public class SchoolSubjectExistsException extends RuntimeException{
    public SchoolSubjectExistsException() {
        super();
    }

    public SchoolSubjectExistsException(String message) {
        super(message);
    }

    public SchoolSubjectExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchoolSubjectExistsException(Throwable cause) {
        super(cause);
    }
}
