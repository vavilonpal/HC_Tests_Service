package org.combs.micro.hc_tests_service.exeptions.existsException;

public class QuestionByThisDescriptionExistsInThisTest extends RuntimeException {
    public QuestionByThisDescriptionExistsInThisTest(String message) {
        super(message);
    }

    public QuestionByThisDescriptionExistsInThisTest(String message, Throwable cause) {
        super(message, cause);
    }
}
