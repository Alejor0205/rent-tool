package com.dardan.rent_tool.domain.validation;

import com.dardan.rent_tool.application.exception.ValidationException;

public class CategoryValidator {

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("category.name", "El nombre de la categoría es obligatorio.");
        }
    }
}
