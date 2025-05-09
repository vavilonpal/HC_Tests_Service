package org.combs.micro.hc_tests_service.exeptions;

public class AttemptTimeSolveExpireException extends RuntimeException {
    public AttemptTimeSolveExpireException() {
        super();
    }

    public AttemptTimeSolveExpireException(String message) {
        super(message);
    }

    public AttemptTimeSolveExpireException(String message, Throwable cause) {
        super(message, cause);
    }
}
