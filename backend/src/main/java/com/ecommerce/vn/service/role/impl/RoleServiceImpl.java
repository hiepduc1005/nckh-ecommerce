package com.ecommerce.vn.service.role.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.vn.entity.role.Role;
import com.ecommerce.vn.repository.RoleRepository;
import com.ecommerce.vn.service.role.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;  

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID id, Role roleDetails) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            
            role.setRoleName(roleDetails.getRoleName());
            role.setPrivileges(roleDetails.getPrivileges());
            return roleRepository.save(role);
        } else {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public void deleteRole(UUID id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.delete(optionalRole.get());
        } else {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public Role getRoleById(UUID id) {
        
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        } else {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}

