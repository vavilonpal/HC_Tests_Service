package org.combs.micro.hc_tests_service.exeptions;

public class SchoolSubjectNotFoundException extends RuntimeException {
    public SchoolSubjectNotFoundException() {
    }

    public SchoolSubjectNotFoundException(String message) {
        super(message);
    }

    public SchoolSubjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchoolSubjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public SchoolSubjectNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
