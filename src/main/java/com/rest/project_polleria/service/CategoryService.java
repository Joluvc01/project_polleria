package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CategoryService {
    public List<Category> findAll();
    public Optional<Category> findById(UUID id);
    public Category save(Category category);
    public void deleteById(UUID id);
}
