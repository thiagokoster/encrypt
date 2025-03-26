package dev.thiagokoster.encrypt.globalhandlers;

import dev.thiagokoster.encrypt.dtos.ErrorResponse;
import dev.thiagokoster.encrypt.exceptions.AuthenticationFailedException;
import dev.thiagokoster.encrypt.exceptions.InvalidUserException;
import dev.thiagokoster.encrypt.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String[] errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                                .toArray(String[]::new);
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserException(InvalidUserException ex) {
        String[] errors = new String[]{ex.getMessage()};
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        String[] errors = new String[] { ex.getMessage() };
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        String[] errors = new String[] { ex.getMessage() };
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.NOT_FOUND);
    }
}
