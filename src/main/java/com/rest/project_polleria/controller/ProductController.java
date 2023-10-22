package com.rest.project_polleria.controller;

import com.rest.project_polleria.dto.ProductDTO;
import com.rest.project_polleria.entity.Category;
import com.rest.project_polleria.entity.Product;
import com.rest.project_polleria.service.CategoryService;
import com.rest.project_polleria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findByCategoryName(@RequestParam(required = false) String categoryName, Pageable pageable) {
        // Verificar si no se ha especificado ninguna ordenación y establecer la predeterminada
        if (pageable.getSort().isEmpty()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
        }

        Page<Product> productsPage;

        if (categoryName != null && !categoryName.isEmpty()) {
            productsPage = productService.findByCategoryName(categoryName, pageable);
        } else {
            productsPage = productService.findAll(pageable);
        }

        // Mapear Product a ProductDTO con nombre de categoría
        Page<ProductDTO> productDTOs = productsPage.map(product -> {
            String category = product.getCategory() != null ? product.getCategory().getName() : null;
            return new ProductDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getImage(),
                    category
            );
        });

        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody List<ProductDTO> productDTOList) {
        List<Product> products = new ArrayList<>();

        // Convierte cada ProductDTO a Product y agrégalo a la lista de productos
        for (ProductDTO productDTO : productDTOList) {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setImage(productDTO.getImage());
            products.add(product);
            // Buscar la categoría por nombre en la base de datos
            Category category = categoryService.findByName(productDTO.getCategoryName());

            if (category == null) {
                // Manejar el caso en el que la categoría no existe
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La categoría no existe");
            }

            product.setCategory(category);
        }

        // Guarda los productos en la base de datos
        productService.saveAll(products);



        // Devuelve la respuesta con la lista de productos creados
        return ResponseEntity.status(HttpStatus.CREATED).body(products);
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

            // Busca la categoría por nombre
            Category category = categoryService.findByName(productDTO.getCategoryName());

            if (category != null) {
                // Establece la categoría en el producto
                existingProduct.setCategory(category);

                // Guarda el producto actualizado en la base de datos
                Product updatedProduct = productService.update(existingProduct);

                // Devuelve la respuesta con el producto actualizado
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada: " + productDTO.getCategoryName());
            }
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
