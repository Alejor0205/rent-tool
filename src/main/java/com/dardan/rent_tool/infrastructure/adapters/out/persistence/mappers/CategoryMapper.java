package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.CategoryEntity;

public class CategoryMapper {

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Category(entity.getId(), entity.getName());
    }

    public CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryEntity(category.getId(), category.getName());
    }
}
