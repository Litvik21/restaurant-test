package com.example.testprojectrestaurant.dto;

import com.example.testprojectrestaurant.lib.FieldsValueMatch;
import com.example.testprojectrestaurant.lib.ValidEmail;
import com.example.testprojectrestaurant.lib.ValidPassword;
import com.example.testprojectrestaurant.lib.ValidUsername;
import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword"
)
public record UserRegisterDto (@ValidEmail
                               String email,
                               @Size(min = 4, max = 16, message = "Must contains 4 - 16 symbols.")
                               @ValidUsername
                               String username,
                               @Size(min = 4, max = 16, message = "Must contains 4 - 16 symbols.")
                               @ValidPassword
                               String password,
                               String repeatPassword) {
}
