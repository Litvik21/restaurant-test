package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User account);

    User update(User account);

    List<User> getAll();

    User get(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Long getIdByUsername(String username);
}
