package com.dardan.rent_tool.application.exception;

public class ConflictException extends ApplicationException {
    public ConflictException(String field, String message) {
        super(field, message);
    }
}
