package ru.sfedu.simplepsy.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class NotFoundException extends RuntimeException {


    public NotFoundException(String message) {
        super(message);
    }

}
