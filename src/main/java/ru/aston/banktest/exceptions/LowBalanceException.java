package ru.aston.banktest.exceptions;

public class LowBalanceException extends RuntimeException{
    public LowBalanceException(String msg){
        super(msg);
    }
}
