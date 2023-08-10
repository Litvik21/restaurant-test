package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role getRoleByRoleName(Role.RoleName roleName);

    List<Role> findAll();
}
