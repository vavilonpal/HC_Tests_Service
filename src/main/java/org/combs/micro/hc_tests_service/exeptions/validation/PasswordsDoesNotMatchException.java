package org.combs.micro.hc_tests_service.exeptions.validation;

public class PasswordsDoesNotMatchException extends RuntimeException{
    public PasswordsDoesNotMatchException() {
        super();
    }

    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }

    public PasswordsDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
