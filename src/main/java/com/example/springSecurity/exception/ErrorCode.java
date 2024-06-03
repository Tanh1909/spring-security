package com.example.springSecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR(9999,"SOMETHING GONE WRONG!",HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001,"USER HAS BEEN EXISTED!",HttpStatus.BAD_REQUEST),
    NOT_FOUND(1002," NOT FOUND!",HttpStatus.BAD_REQUEST),
    WRONG_USERNAME_PASSWORD(1004,"WRONG USERNAME OR PASSWORD!",HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(1005,"WRONG USERNAME OR PASSWORD!",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006,"YOU NEED PERMISSIONS",HttpStatus.FORBIDDEN),
    LOGGED_OUT(1007,"YOU ARE LOGGED OUT",HttpStatus.UNAUTHORIZED)
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;



}
