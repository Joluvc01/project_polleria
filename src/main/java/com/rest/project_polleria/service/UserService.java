package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    public List<User> findAll();
    public Optional<User> findById(UUID id);
    public User save(User user);
    public void delete(UUID id);
}
