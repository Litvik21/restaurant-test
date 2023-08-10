package com.example.testprojectrestaurant.lib;

import com.example.testprojectrestaurant.service.UserService;
import lombok.AllArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String>  {
    private static final String EMAIL_VALIDATION_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Fill the email field.")
                    .addConstraintViolation();
            return false;
        }
        if (userService.findByEmail(email).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("This email is already register.")
                    .addConstraintViolation();
            return false;
        }
        return email.matches(EMAIL_VALIDATION_REGEX);
    }
}
