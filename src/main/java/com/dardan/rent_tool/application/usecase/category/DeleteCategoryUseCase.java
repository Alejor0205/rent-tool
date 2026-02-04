package com.dardan.rent_tool.application.usecase.category;

import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.port.out.CategoryOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCategoryUseCase {

    private final CategoryOutputPort categoryOutputPort;

    public DeleteCategoryUseCase(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    public void execute(UUID id) {
        if (categoryOutputPort.findById(id).isEmpty()) {
            throw new NotFoundException("categoryId", "La categoría no existe.");
        }
        categoryOutputPort.deleteById(id);
    }
}
