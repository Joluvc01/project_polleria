package com.rest.project_polleria.service;

import com.rest.project_polleria.entity.Product;
import com.rest.project_polleria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }
    @Override
    public Page<Product> findByCategoryName(String categoryName, Pageable pageable) { return productRepository.findByCategoryName(categoryName, pageable);}

    @Override
    public Iterable<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
