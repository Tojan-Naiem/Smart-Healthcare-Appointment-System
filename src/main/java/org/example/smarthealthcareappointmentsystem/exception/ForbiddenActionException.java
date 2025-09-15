package org.example.smarthealthcareappointmentsystem.exception;

public class ForbiddenActionException extends RuntimeException{
    public ForbiddenActionException(String message){
        super(message);
    }
}
