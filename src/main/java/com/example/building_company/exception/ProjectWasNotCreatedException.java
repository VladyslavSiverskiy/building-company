package com.example.building_company.exception;

public class ProjectWasNotCreatedException extends RuntimeException {
    public ProjectWasNotCreatedException(String message) {
        super(message);
    }
}
