package com.solera.registers.exception;

public class RegisterNotFoundException extends RuntimeException{
    public RegisterNotFoundException(String message){
        super(message);
    }
}
