package com.rest.project_polleria.controller;

import com.rest.project_polleria.dto.ProductDTO;
import com.rest.project_polleria.entity.Category;
import com.rest.project_polleria.entity.Product;
import com.rest.project_polleria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){
        // Verificar si no se ha especificado ninguna ordenación y establecer la predeterminada
        if (pageable.getSort().isEmpty()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
        }
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Product>> findByCategoryId(@PathVariable UUID categoryId, Pageable pageable){
        // Verificar si no se ha especificado ninguna ordenación y establecer la predeterminada
        if (pageable.getSort().isEmpty()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
        }
        return ResponseEntity.ok(productService.findByCategoryId(categoryId, pageable));

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        // Convierte ProductDTO a Product
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());

        // Guarda el producto en la base de datos
        productService.save(product);

        // Devuelve la respuesta con el producto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> update(@PathVariable UUID productId, @RequestBody ProductDTO productDTO) {
        // Verifica si el producto con el ID especificado existe en la base de datos
        Optional<Product> optionalProduct = productService.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Actualiza los atributos del producto con los valores del DTO
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setStock(productDTO.getStock());
            existingProduct.setImage(productDTO.getImage());
            existingProduct.setCategory(productDTO.getCategory());

            // Guarda el producto actualizado en la base de datos
            Product updatedProduct = productService.save(existingProduct);

            // Devuelve la respuesta con el producto actualizado
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + productId);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProducto(@PathVariable UUID productId) {
        // Verifica si la categoria con el ID especificado existe en la base de datos
        Optional<Product> optionalProduct = productService.findById(productId);

        if (optionalProduct.isPresent()) {
            // Elimina la categoria con el ID especificado
            productService.deleteById(productId);

            // Devuelve la respuesta con el mensaje de eliminación exitosa
            return ResponseEntity.ok("Categoria eliminada con ID: " + productId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrada con ID: " + productId);
        }
    }

}
