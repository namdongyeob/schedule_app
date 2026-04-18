package com.example.schedule_app.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ServiceException {
    public InvalidPasswordException(String message){
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
