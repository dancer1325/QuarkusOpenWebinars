package org.openwebinars.course.gettingStarted;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.FIELD})
@Documented
@Constraint(validatedBy = {NotExpiredValidator.class})
public @interface NotExpired {
    // Message to display in case of error
    String message() default "Beer must not be expired";

    // Group validations
    Class<?>[] groups() default {};

    // Add extra information about the root cause

    Class<? extends Payload>[] payload() default {};
}
