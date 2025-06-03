package org.combs.micro.hc_tests_service.exeptions.notFound;

public class SchoolTestNotFoundException extends RuntimeException {
    public SchoolTestNotFoundException() {
    }

    public SchoolTestNotFoundException(String message) {
        super(message);
    }

    public SchoolTestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchoolTestNotFoundException(Throwable cause) {
        super(cause);
    }

    public SchoolTestNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
