package com.example.building_company.exception;

public class UserNotFoundException extends RuntimeException {
    /**
     * Constructor for UserNotFoundException.
     *
     * @param message - specified message.
     * @author Nazar Klimovych
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
