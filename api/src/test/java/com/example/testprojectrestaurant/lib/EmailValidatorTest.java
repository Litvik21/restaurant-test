package com.example.testprojectrestaurant.lib;

import com.example.testprojectrestaurant.service.UserService;
import com.example.testprojectrestaurant.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.validation.ConstraintValidatorContext;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private EmailValidator emailValidator;
    private ConstraintValidatorContext validatorContext;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(UserServiceImpl.class);
        validatorContext = Mockito.mock(ConstraintValidatorContext.class);
        emailValidator = new EmailValidator(service);
    }

    @Test
    void isValid_Ok() {
        assertTrue(emailValidator.isValid("litvik@gmail.com", validatorContext));
        assertTrue(emailValidator.isValid("petr12@icloud.com", validatorContext));
    }

    @Test
    void isValid_NotOk() {
        assertFalse(emailValidator.isValid("litvik", validatorContext));
        assertFalse(emailValidator.isValid("@gmail.com", validatorContext));
        assertFalse(emailValidator.isValid("111gggrgjg@gmailjgjjgjgjgjgjgjg", validatorContext));
    }

}