package dev.thiagokoster.encrypt.GlobalHandlers;

import dev.thiagokoster.encrypt.DTOs.ErrorResponse;
import dev.thiagokoster.encrypt.Exceptions.InvalidUserException;
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
}
