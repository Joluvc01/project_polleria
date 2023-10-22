package com.rest.project_polleria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO implements Serializable {

    final String name;
    final String description;
    final String image;
}
