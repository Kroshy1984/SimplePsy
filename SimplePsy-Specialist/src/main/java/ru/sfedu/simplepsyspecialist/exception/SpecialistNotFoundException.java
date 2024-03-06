package ru.sfedu.simplepsyspecialist.exception;

public class SpecialistNotFoundException extends RuntimeException{
    public SpecialistNotFoundException(String message)
    {
        super(message);
    }
}
