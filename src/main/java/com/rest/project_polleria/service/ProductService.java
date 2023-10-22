package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface ProductService {
    public Page<Product> findAll(Pageable pageable);
    public Optional<Product> findById(UUID id);
    public Page<Product> findByCategoryId(UUID categoryId, Pageable pageable);
    public Product save(Product product);
    public void deleteById(UUID id);

}
