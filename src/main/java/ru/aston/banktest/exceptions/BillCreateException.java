package ru.aston.banktest.exceptions;

public class BillCreateException extends RuntimeException {
    public BillCreateException(String msg) {
        super(msg);
    }
}
