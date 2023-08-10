package com.example.testprojectrestaurant.controller;

import com.example.testprojectrestaurant.dto.UserResponseDto;
import com.example.testprojectrestaurant.dto.mapper.UserMapper;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public UserResponseDto getById(@PathVariable(name = "id") Long id) {
        return mapper.toDto(service.get(id));
    }

}
