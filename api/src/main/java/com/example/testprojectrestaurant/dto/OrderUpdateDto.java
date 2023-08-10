package com.example.testprojectrestaurant.dto;

public record OrderUpdateDto (String product,
                              String function,
                              Long userId) {
}
