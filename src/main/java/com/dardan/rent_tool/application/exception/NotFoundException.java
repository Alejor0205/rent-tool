package com.dardan.rent_tool.application.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String field, String message) {
        super(field, message);
    }
}
