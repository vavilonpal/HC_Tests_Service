package org.combs.micro.hc_tests_service.exeptions;

public class ResultNotFoundException extends RuntimeException {
    public ResultNotFoundException() {
    }

    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResultNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
