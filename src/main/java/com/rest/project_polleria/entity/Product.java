package com.rest.project_polleria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @NotNull
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

}
