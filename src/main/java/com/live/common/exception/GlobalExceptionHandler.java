package com.live.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handle(DuplicateEmailException e) {
        log.error("DuplicateEmailException", e);
        return ErrorResponse.of(HttpStatus.CONFLICT.value(), "CONFLICT", e.getMessage()).toResponseEntity();
    }

    @ExceptionHandler({InvalidEmailException.class, InvalidNameException.class})
    public ResponseEntity<ErrorResponse> handle(RuntimeException e) {
        log.error("InvalidRequest", e);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", e.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("ValidationError: {}", msg);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", msg).toResponseEntity();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException e) {
        log.error("UserNotFound", e);
        return ErrorResponse.of(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", e.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(CouponNotFoundException e) {
        log.error("CouponNotFound", e);
        return ErrorResponse.of(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", e.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(CouponDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handle(CouponDuplicatedException e) {
        log.error("CouponDuplicate", e);
        return ErrorResponse.of(HttpStatus.CONFLICT.value(), "CONFLICT", e.getMessage()).toResponseEntity();
    }

    @ExceptionHandler(CouponExpireException.class)
    public ResponseEntity<ErrorResponse> handle(CouponExpireException e) {
        log.error("CouponExpire", e);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", e.getMessage()).toResponseEntity();
    }
}
