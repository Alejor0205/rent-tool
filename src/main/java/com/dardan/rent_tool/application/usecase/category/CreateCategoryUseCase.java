package com.dardan.rent_tool.application.usecase.category;

import com.dardan.rent_tool.application.command.CreateCategoryCommand;
import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.application.exception.ConflictException;
import com.dardan.rent_tool.application.mapper.CategoryDTOMapper;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import com.dardan.rent_tool.domain.model.entity.Category;
import com.dardan.rent_tool.domain.validation.CategoryValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCase {

    private final CategoryOutputPort categoryOutputPort;
    private final CategoryValidator validator = new CategoryValidator();
    private final CategoryDTOMapper mapper = new CategoryDTOMapper();

    public CreateCategoryUseCase(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    public CategoryDTO execute(CreateCategoryCommand command) {
        validator.validateName(command.getName());
        String name = command.getName().trim();
        if (categoryOutputPort.existsByNameIgnoreCase(name)) {
            throw new ConflictException("category.name", "La categoría ya existe.");
        }
        Category saved = categoryOutputPort.save(new Category(null, name));
        return mapper.toDTO(saved);
    }
}
