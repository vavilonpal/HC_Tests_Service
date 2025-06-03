package org.combs.micro.hc_tests_service.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.combs.micro.hc_tests_service.validation.validators.PasswordMatchValidator;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {
    String message() default "Passwords doesn't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
