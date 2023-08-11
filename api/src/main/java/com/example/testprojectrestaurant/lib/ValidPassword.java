package com.example.testprojectrestaurant.lib;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default """
            Contains at least one digit.
            Contains at least one lowercase letter.
            Contains at least one uppercase letter.""";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
