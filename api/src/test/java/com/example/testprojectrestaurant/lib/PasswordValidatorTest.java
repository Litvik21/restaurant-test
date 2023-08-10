package com.example.testprojectrestaurant.lib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.validation.ConstraintValidatorContext;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    private PasswordValidator passwordValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void isValid_Ok() {
        assertTrue(passwordValidator.isValid("Litvik123", context));
        assertTrue(passwordValidator.isValid("z345Xyz", context));
        assertTrue(passwordValidator.isValid("_9Def", context));
    }

    @Test
    void isValid_NotOk() {
        assertFalse(passwordValidator.isValid("gjgjgjjg45", context));
        assertFalse(passwordValidator.isValid("12345678", context));
        assertFalse(passwordValidator.isValid("GHGHGH31", context));
        assertFalse(passwordValidator.isValid("Gjgjpre", context));
        assertFalse(passwordValidator.isValid("ggjjgjgj", context));
        assertFalse(passwordValidator.isValid("GJGJJGJGJG", context));
    }

}