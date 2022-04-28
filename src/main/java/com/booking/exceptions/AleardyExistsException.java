package com.booking.exceptions;

public class AleardyExistsException extends RuntimeException {

    private String message;
    public AleardyExistsException(String message) {
        super(message);
        this.message = message;
    }


}
