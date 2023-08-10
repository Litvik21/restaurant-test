package com.example.testprojectrestaurant.controller;

import com.example.testprojectrestaurant.dto.UserLoginDto;
import com.example.testprojectrestaurant.dto.UserRegisterDto;
import com.example.testprojectrestaurant.dto.UserResponseDto;
import com.example.testprojectrestaurant.dto.UserResponseLoggingDto;
import com.example.testprojectrestaurant.dto.mapper.UserMapper;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.security.AuthenticationService;
import com.example.testprojectrestaurant.security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider provider;
    private final UserMapper userMapper;


    @PostMapping("/register")
    @ApiOperation(value = "registration new account for user")
    public UserResponseDto register(@RequestBody @Valid UserRegisterDto dto) {
        User account = authenticationService.addNewAccount(dto.email(), dto.username(),
                dto.password());
        return userMapper.toDto(account);
    }

    @PostMapping("/login")
    @ApiOperation(value = "login to account")
    public UserResponseLoggingDto login(@RequestBody @Valid UserLoginDto dto) {
        User account = authenticationService.login(dto.username(), dto.password());
        String token = provider.createToken(account.getUsername(), account.getRole().stream()
                .map(r -> r.getRoleName().name())
                .collect(Collectors.toList()));
        return userMapper.toDto(token, account);
    }
}
