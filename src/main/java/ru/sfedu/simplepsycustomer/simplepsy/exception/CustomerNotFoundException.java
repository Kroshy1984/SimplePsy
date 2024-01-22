package ru.sfedu.simplepsycustomer.simplepsy.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
