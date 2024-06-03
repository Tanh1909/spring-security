package com.example.springSecurity.exception;

import com.example.springSecurity.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception exception){
        log.error(exception.getMessage());
        ErrorCode e=ErrorCode.INTERNAL_ERROR;
        return ResponseEntity.internalServerError().body(ApiResponse.error(e.getCode(), e.getMessage()));
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appException(AppException appException){
        ErrorCode e=appException.getErrorCode();
        return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.error(e.getCode(), e.getMessage()));
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException authenticationException){
        ErrorCode e=ErrorCode.UNAUTHENTICATED;
        return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.error(e.getCode(), e.getMessage()));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException accessDeniedException){
        ErrorCode e=ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.error(e.getCode(), e.getMessage()));
    }
}
