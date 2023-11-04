package com.example.building_company.exception;

public class ProjectNotFoundException extends RuntimeException {
    /**
     * Constructor for ProjectNotFoundException.
     *
     * @param message - specified message.
     * @author Vladyslav Siverskyi
     */
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
