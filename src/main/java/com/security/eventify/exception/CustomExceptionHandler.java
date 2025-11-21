package com.security.eventify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiErreur> userNotFoundException(UserNotFound e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(404);
        return new ResponseEntity<>(apiErreur, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ApiErreur> userAlreadyExists(UserAlreadyExists e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(409);
        return new ResponseEntity<>(apiErreur, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErreur> eventNotFound(EventNotFoundException e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(404);
        return new ResponseEntity<>(apiErreur, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ApiErreur> unauthorizedAction(UnauthorizedActionException e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException(e.getMessage());
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(403);
        return new ResponseEntity<>(apiErreur, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErreur> badCredentials(BadCredentialsException e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException("Identifiants invalides");
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(401);
        return new ResponseEntity<>(apiErreur, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErreur> usernameNotFound(UsernameNotFoundException e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException("Utilisateur non trouvé");
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(404);
        return new ResponseEntity<>(apiErreur, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        errors.put("code", 400);
        errors.put("exception", "Erreur de validation");
        errors.put("errors", fieldErrors);
        errors.put("date", LocalDateTime.now());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErreur> handleGenericException(Exception e) {
        ApiErreur apiErreur = new ApiErreur();
        apiErreur.setException("Erreur interne du serveur");
        apiErreur.setDate(LocalDateTime.now());
        apiErreur.setCode(500);
        return new ResponseEntity<>(apiErreur, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}