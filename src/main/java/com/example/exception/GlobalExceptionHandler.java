package com.example.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

/* Аннотация @ControllerAdvice используется в Spring MVC для обработки
 * исключений, которые могут возникнуть в приложении. Эта аннотация определяет
 * класс, который будет обрабатывать исключения, бросаемые в
 * контроллерах приложения.
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    /* Аннотация @ExceptionHandler используется для обработки определенного
     * типа исключения. Метод, помеченный этой аннотацией, будет вызываться
     * при возникновении исключения в контроллере.
     * */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(
            EntityNotFoundException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(),
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
