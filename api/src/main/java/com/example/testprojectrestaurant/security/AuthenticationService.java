package com.example.testprojectrestaurant.security;

import com.example.testprojectrestaurant.model.User;

public interface AuthenticationService {
    User addNewAccount(String email, String username, String password);

    User login(String username, String password);
}
