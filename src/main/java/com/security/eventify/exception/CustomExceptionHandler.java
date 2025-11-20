package com.security.eventify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiErreur> userNotFoundException(UserNotFound e){
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(404);
        return new ResponseEntity<>(apiErreur, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ApiErreur> userAlreadyExists(UserAlreadyExists e){
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(409);
        return new ResponseEntity<>(apiErreur,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErreur> eventNotFound(EventNotFoundException e){
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(404);
        return new ResponseEntity<>(apiErreur,HttpStatus.NOT_FOUND);
    }
}
