package com.dardan.rent_tool.application.mapper;

import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.domain.model.entity.Category;

public class CategoryDTOMapper {
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getId(), category.getName());
    }
}
