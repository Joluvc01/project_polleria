package com.rest.project_polleria.controller;

import com.rest.project_polleria.entity.Category;
import com.rest.project_polleria.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestBody Category updatedCategory) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            category.setImage(updatedCategory.getImage());  // Update the image as needed
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        categoryRepository.deleteById(id);
    }
}
