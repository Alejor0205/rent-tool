package com.dardan.rent_tool.application.exception;

public class ValidationException extends ApplicationException {
    public ValidationException(String field, String message) {
        super(field, message);
    }
}
