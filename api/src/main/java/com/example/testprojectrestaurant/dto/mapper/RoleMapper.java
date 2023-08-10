package com.example.testprojectrestaurant.dto.mapper;

import com.example.testprojectrestaurant.dto.RoleResponseDto;
import com.example.testprojectrestaurant.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponseDto toDto(Role role) {
        return new RoleResponseDto(
                role.getId(),
                role.getRoleName().name());
    }
}
