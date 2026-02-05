package com.dardan.rent_tool.application.port.in;

import com.dardan.rent_tool.application.command.CreateCategoryCommand;
import com.dardan.rent_tool.application.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryUseCase {
    CategoryDTO create(CreateCategoryCommand command);

    CategoryDTO get(UUID id);

    List<CategoryDTO> list();

    void delete(UUID id);
}
