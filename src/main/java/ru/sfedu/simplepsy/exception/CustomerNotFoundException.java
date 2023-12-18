package ru.sfedu.simplepsy.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
