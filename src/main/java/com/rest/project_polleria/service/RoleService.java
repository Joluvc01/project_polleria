package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface RoleService {
    public List<Role> findAll();
    public Optional<Role> findById(UUID id);
    public Role save(Role role);
    public void deleteById(UUID id);
}