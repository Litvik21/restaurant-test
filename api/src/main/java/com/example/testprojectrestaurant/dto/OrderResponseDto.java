package com.example.testprojectrestaurant.dto;

public record OrderResponseDto (Long id,
                                String product,
                                String status,
                                Long userId) {
}
