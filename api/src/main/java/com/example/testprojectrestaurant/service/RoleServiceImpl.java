package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.Role;
import com.example.testprojectrestaurant.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getRoleByRoleName(Role.RoleName roleName) {
        return repository.getRoleByRoleName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}
