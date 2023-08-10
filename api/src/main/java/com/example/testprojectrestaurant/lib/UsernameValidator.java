package com.example.testprojectrestaurant.lib;

import com.example.testprojectrestaurant.service.UserService;
import lombok.AllArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Fill the username field.")
                    .addConstraintViolation();
            return false;
        }
        if (userService.findByUsername(username).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("This username is already taken.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
