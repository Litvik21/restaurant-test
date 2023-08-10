package com.example.testprojectrestaurant.dto.mapper;

import com.example.testprojectrestaurant.dto.UserRegisterDto;
import com.example.testprojectrestaurant.dto.UserResponseDto;
import com.example.testprojectrestaurant.dto.UserResponseLoggingDto;
import com.example.testprojectrestaurant.model.Role;
import com.example.testprojectrestaurant.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashSet;

@AllArgsConstructor
@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    public UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getRole().stream().map(roleMapper::toDto).toList());
    }

    public User toModel(UserRegisterDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(new HashSet<>());
        return user;
    }

    public UserResponseLoggingDto toDto(String token, User user) {
        return new UserResponseLoggingDto(
                user.getId(),
                token,
                user.getEmail(),
                user.getUsername(),
                user.getRole().stream().map(Role::getRoleName).findFirst().get().name());
    }
}
