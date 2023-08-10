package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find account by id: " + id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Long getIdByUsername(String username) {
        User user = findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Can't find user bu username: " + username));
        return user.getId();
    }
}
