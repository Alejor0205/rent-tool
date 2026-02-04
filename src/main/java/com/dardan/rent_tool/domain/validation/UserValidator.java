package com.dardan.rent_tool.domain.validation;

import com.dardan.rent_tool.application.exception.ValidationException;
import com.dardan.rent_tool.domain.model.enumm.RoleType;

public class UserValidator {

    public void validateFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new ValidationException("user.fullName", "El nombre completo es obligatorio.");
        }
    }

    public void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("user.email", "El email es obligatorio.");
        }
        if (!email.contains("@")) {
            throw new ValidationException("user.email", "El email no es válido.");
        }
    }

    public void validateRole(RoleType role) {
        if (role == null) {
            throw new ValidationException("user.role", "El rol es obligatorio.");
        }
    }
}
