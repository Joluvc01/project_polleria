package com.rest.project_polleria.controller;

import com.rest.project_polleria.dto.CategoryDTO;
import com.rest.project_polleria.entity.Category;
import com.rest.project_polleria.repository.CategoryRepository;
import com.rest.project_polleria.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody List<CategoryDTO> categoryDTOList) {
        List<Category> categories = new ArrayList<>();

        // Convierte cada CategoryDTO a Category y agrégalos a la lista
        for (CategoryDTO categoryDTO : categoryDTOList) {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setImage(categoryDTO.getImage());
            categories.add(category);
        }

        // Guarda las categorías en la base de datos
        categoryService.saveAll(categories);

        // Devuelve la respuesta con las categorías creadas
        return ResponseEntity.status(HttpStatus.CREATED).body(categories);
    }


    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> update(@PathVariable UUID categoryId, @RequestBody CategoryDTO categoryDTO) {
        // Verifica si la categoria con el ID especificado existe en la base de datos
        Optional<Category> optionalCategory = categoryService.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();

            // Actualiza los atributos de la categoria con los valores del DTO
            existingCategory.setName(categoryDTO.getName());
            existingCategory.setDescription(categoryDTO.getDescription());
            existingCategory.setImage(categoryDTO.getImage());

            // Guarda la categoria actualizada en la base de datos
            Category updatedCategory = categoryService.save(existingCategory);

            // Devuelve la respuesta con la categoria actualizada
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada con ID: " + categoryId);
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable UUID categoryId) {
        // Verifica si la categoria con el ID especificado existe en la base de datos
        Optional<Category> optionalCategory = categoryService.findById(categoryId);

        if (optionalCategory.isPresent()) {
            // Elimina la categoria con el ID especificado
            categoryService.deleteById(categoryId);

            // Devuelve la respuesta con el mensaje de eliminación exitosa
            return ResponseEntity.ok("Categoria eliminada con ID: " + categoryId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada con ID: " + categoryId);
        }
    }
}