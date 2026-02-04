package com.dardan.rent_tool.infrastructure.adapters.out.persistence.mappers;

import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.domain.model.entity.RentTool;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import com.dardan.rent_tool.infrastructure.adapters.out.persistence.entity.ToolEntity;

public class ToolMapper {

    public RentTool toDomain(ToolEntity entity) {
        if (entity == null) {
            return null;
        }
        CategoryEntity categoryEntity = entity.getCategory();
        Category category = null;
        if (categoryEntity != null) {
            category = new Category(categoryEntity.getId(), categoryEntity.getName());
        }
        return new RentTool(
            entity.getId(),
            entity.getName(),
            category,
            entity.getHourlyRate(),
            entity.getDailyRate(),
            entity.getStatus()
        );
    }

    public ToolEntity toEntity(RentTool tool, CategoryEntity categoryEntity) {
        if (tool == null) {
            return null;
        }
        return new ToolEntity(
            tool.getId(),
            tool.getName(),
            categoryEntity,
            tool.getHourlyRate(),
            tool.getDailyRate(),
            tool.getStatus()
        );
    }
}
