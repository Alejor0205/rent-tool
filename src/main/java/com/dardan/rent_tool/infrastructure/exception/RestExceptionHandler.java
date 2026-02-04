package com.dardan.rent_tool.infrastructure.exception;

import com.dardan.rent_tool.application.exception.ApplicationException;
import com.dardan.rent_tool.application.exception.ConflictException;
import com.dardan.rent_tool.application.exception.NotFoundException;
import com.dardan.rent_tool.application.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(ValidationException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(NotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(ConflictException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiErrorResponse> handleApplication(ApplicationException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse(
            Instant.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            null,
            "Error inesperado. Intenta más tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, ApplicationException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
            Instant.now(),
            status.value(),
            ex.getField(),
            ex.getMessage()
        );
        return ResponseEntity.status(status).body(response);
    }
}
