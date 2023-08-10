package com.example.testprojectrestaurant.repository;

import com.example.testprojectrestaurant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(Role.RoleName roleName);
}
