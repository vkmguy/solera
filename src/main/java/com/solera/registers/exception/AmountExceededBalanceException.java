package com.solera.registers.exception;

public class AmountExceededBalanceException extends RuntimeException{
    public AmountExceededBalanceException(String message) {
        super(message);
    }
}
