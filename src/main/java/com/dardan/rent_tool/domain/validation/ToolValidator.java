package com.dardan.rent_tool.domain.validation;

import com.dardan.rent_tool.application.exception.ValidationException;

import java.math.BigDecimal;

public class ToolValidator {

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("tool.name", "El nombre de la herramienta es obligatorio.");
        }
    }

    public void validateCategoryId(Object categoryId) {
        if (categoryId == null) {
            throw new ValidationException("tool.categoryId", "La categoría es obligatoria.");
        }
    }

    public void validateRates(BigDecimal hourlyRate, BigDecimal dailyRate) {
        if (hourlyRate == null && dailyRate == null) {
            throw new ValidationException("tool.rates", "Debe definir tarifa por hora o por día.");
        }
        if (hourlyRate != null && hourlyRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("tool.hourlyRate", "La tarifa por hora no puede ser negativa.");
        }
        if (dailyRate != null && dailyRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("tool.dailyRate", "La tarifa por día no puede ser negativa.");
        }
    }
}
