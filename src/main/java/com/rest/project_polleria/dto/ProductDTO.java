package com.rest.project_polleria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO implements Serializable {
    final String name;
    final String description;
    final Double price;
    final Integer stock;
    final String image;
    final String categoryName; // Cambio para recibir el nombre de la categoría
}
