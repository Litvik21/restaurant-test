package com.example.testprojectrestaurant.dto;

public record OrderResponseDto (Long id,
                                String product,
                                String function,
                                Long userId) {
}
