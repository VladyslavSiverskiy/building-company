package com.example.building_company.exception;

public class ProjectWasNotUpdatedException extends RuntimeException {
    public ProjectWasNotUpdatedException(String projectWasNotCreatedImagesError) {
        super(projectWasNotCreatedImagesError);
    }
}
