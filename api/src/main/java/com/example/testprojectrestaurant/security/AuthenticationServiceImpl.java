package com.example.testprojectrestaurant.security;

import com.example.testprojectrestaurant.model.Role;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.service.RoleService;
import com.example.testprojectrestaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User addNewAccount(String email, String username, String password) {
        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Set.of(roleService.getRoleByRoleName(Role.RoleName.USER)));
        return userService.add(user);
    }

    @Override
    public User login(String username, String password) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Incorrect username or password!");
        }
        return user.get();
    }
}
