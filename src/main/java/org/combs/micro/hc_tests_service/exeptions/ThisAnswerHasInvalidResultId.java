package org.combs.micro.hc_tests_service.exeptions;

public class ThisAnswerHasInvalidResultId extends RuntimeException {
    public ThisAnswerHasInvalidResultId() {
    }

    public ThisAnswerHasInvalidResultId(String message) {
        super(message);
    }

    public ThisAnswerHasInvalidResultId(String message, Throwable cause) {
        super(message, cause);
    }
}
