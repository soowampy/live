package com.live.common.exception;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, message, LocalDateTime.now());
    }

    public ResponseEntity<ErrorResponse> toResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }
}