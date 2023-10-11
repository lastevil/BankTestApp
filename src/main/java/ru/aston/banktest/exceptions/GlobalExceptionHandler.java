package ru.aston.banktest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BillCreateException.class)
    public ResponseEntity<ApplicationError> handleBillCreateException(BillCreateException exception) {
        log.debug(exception.getMessage());
        return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApplicationError> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.debug(exception.getMessage());
        return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationError> handleValidationException(ValidationException exception) {
        log.debug(exception.getMessage());
        return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity<ApplicationError> handleLowBalanceException(LowBalanceException exception) {
        log.debug(exception.getMessage());
        return new ResponseEntity<>(new ApplicationError(HttpStatus.CONFLICT.value(), exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IndexOutOfBoundsException.class,
            NumberFormatException.class, ArithmeticException.class, ArrayIndexOutOfBoundsException.class,})
    public ResponseEntity<ApplicationError> handleAllOtherExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now() + " " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
