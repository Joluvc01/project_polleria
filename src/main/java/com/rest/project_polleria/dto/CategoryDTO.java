package com.rest.project_polleria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO implements Serializable {

    final UUID id;
    final String name;
    final String description;
    final String image;
}
