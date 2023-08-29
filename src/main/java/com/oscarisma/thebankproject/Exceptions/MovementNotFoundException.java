package com.oscarisma.thebankproject.Exceptions;

public class MovementNotFoundException extends RuntimeException{
    public MovementNotFoundException(String message){
        super(message);
    }
}
