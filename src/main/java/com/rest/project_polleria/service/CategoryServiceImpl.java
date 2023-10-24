package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.Category;
import com.rest.project_polleria.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() { return categoryRepository.findAll();}
    @Override
    public Category findByName(String name) { return categoryRepository.findByName(name);}

    @Override
    public Iterable<Category> saveAll(List<Category> categories) { return categoryRepository.saveAll(categories);}
    @Override
    public Optional<Category> findById(UUID id) { return categoryRepository.findById(id);}
    @Override
    public Category save(Category category) { return categoryRepository.save(category);}
    @Override
    public void deleteById(UUID id) { categoryRepository.deleteById(id);}
}
