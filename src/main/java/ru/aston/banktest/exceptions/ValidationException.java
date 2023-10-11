package ru.aston.banktest.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
