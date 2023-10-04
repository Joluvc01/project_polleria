package com.rest.project_polleria.repository;

import com.rest.project_polleria.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
