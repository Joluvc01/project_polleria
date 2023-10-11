package com.rest.project_polleria.controller;

import com.rest.project_polleria.dto.ProductDTO;
import com.rest.project_polleria.entity.Product;
import com.rest.project_polleria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<Product>> FindAll(Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindById(@PathVariable UUID id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
        // Puedes establecer otros atributos según sea necesario

        // Guarda el producto en la base de datos
        Product savedProduct = productService.save(product);

        // Devuelve la respuesta con el producto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
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
            // Puedes actualizar otros atributos según sea necesario

            // Guarda el producto actualizado en la base de datos
            Product updatedProduct = productService.save(existingProduct);

            // Devuelve la respuesta con el producto actualizado
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + productId);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
