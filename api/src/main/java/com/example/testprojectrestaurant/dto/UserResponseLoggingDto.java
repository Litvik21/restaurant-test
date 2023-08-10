package com.example.testprojectrestaurant.dto;

public record UserResponseLoggingDto (Long id,
                                      String token,
                                      String email,
                                      String username,
                                      String role) {
}
