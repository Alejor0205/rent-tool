package com.dardan.rent_tool.application.usecase.category;

import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.application.mapper.CategoryDTOMapper;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCategoriesUseCase {

    private final CategoryOutputPort categoryOutputPort;
    private final CategoryDTOMapper mapper = new CategoryDTOMapper();

    public ListCategoriesUseCase(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    public List<CategoryDTO> execute() {
        return categoryOutputPort.findAll().stream().map(mapper::toDTO).toList();
    }
}
