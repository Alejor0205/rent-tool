package com.dardan.rent_tool.infrastructure.adapters.out.persistence.repository;

import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
