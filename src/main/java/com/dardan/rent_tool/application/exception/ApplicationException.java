package com.dardan.rent_tool.application.exception;

public class ApplicationException extends RuntimeException {
    private final String field;

    public ApplicationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
