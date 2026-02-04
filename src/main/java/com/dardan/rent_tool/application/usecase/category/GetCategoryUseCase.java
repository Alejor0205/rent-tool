package com.dardan.rent_tool.application.usecase.category;

import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.mapper.CategoryDTOMapper;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCategoryUseCase {

    private final CategoryOutputPort categoryOutputPort;
    private final CategoryDTOMapper mapper = new CategoryDTOMapper();

    public GetCategoryUseCase(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    public CategoryDTO execute(UUID id) {
        return categoryOutputPort.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("categoryId", "La categoría no existe."));
    }
}
