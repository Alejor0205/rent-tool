package com.dardan.rent_tool.infrastructure.exception;

import java.time.Instant;

public class ApiErrorResponse {
    private Instant timestamp;
    private int status;
    private String field;
    private String message;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(Instant timestamp, int status, String field, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.field = field;
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
