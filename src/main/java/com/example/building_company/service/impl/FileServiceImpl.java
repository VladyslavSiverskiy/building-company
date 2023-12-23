package com.example.building_company.service.impl;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.ProjectDto;
import com.example.building_company.exception.ProjectWasNotCreatedException;
import com.example.building_company.exception.ProjectWasNotUpdatedException;
import com.example.building_company.service.FileService;
import com.example.building_company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.example.building_company.constants.AppConstants.*;
import static com.example.building_company.constants.AppConstants.RESOURCES_FOLDER_NAME;
import static com.example.building_company.constants.ExceptionMessages.ERROR_WHILE_DELETING_FILE;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ProjectService projectService;

    /**
     * @param pathInStaticFolder - path to image in static folder
     *                           (for ex: /pictures/id7/image.png)
     */
    public void deleteImage(String pathInStaticFolder) {
        Path path = Paths.get(PATH_TO_STATIC_DIRECTORY, pathInStaticFolder);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(ERROR_WHILE_DELETING_FILE + e.getMessage());
        }
    }

    public void updateProjectImages(
            ProjectDto projectDto,
            ProjectDto projectDtoToUpdate,
            MultipartFile titleImage,
            MultipartFile[] files) {

        if (Objects.isNull(titleImage)) {
            projectDto.setTitleImageLink(projectDtoToUpdate.getTitleImageLink());
        } else {
            try {
                String fileDirectoryName = FULL_PATH_TO_UPLOAD_DIRECTORY + projectDto.getId() + File.separator;
                Path dirPath = Paths.get(fileDirectoryName);
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Path fileNameAndPath = Paths.get(String.valueOf(dirPath), titleImage.getOriginalFilename());
                if (Objects.requireNonNull(titleImage.getOriginalFilename()).length() > 3) {
                    Files.write(fileNameAndPath, titleImage.getBytes());
                    String url = String.valueOf(fileNameAndPath);
                    url = url.substring(url.indexOf(RESOURCES_FOLDER_NAME) + RESOURCES_FOLDER_NAME.length());
                    projectDto.setTitleImageLink(url);
                }
            } catch (IOException ex) {
                throw new ProjectWasNotUpdatedException(ExceptionMessages.PROJECT_WAS_NOT_CREATED_IMAGES_ERROR);
            }
        }

        try {
            String fileDirectoryName = FULL_PATH_TO_UPLOAD_DIRECTORY + projectDto.getId() + File.separator;
            Path dirPath = Paths.get(fileDirectoryName);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            for (MultipartFile file : files) {
                Path fileNameAndPath = Paths.get(String.valueOf(dirPath), file.getOriginalFilename());
                if (Objects.requireNonNull(file.getOriginalFilename()).length() > 3) {
                    Files.write(fileNameAndPath, file.getBytes());
                    String url = String.valueOf(fileNameAndPath);
                    url = url.substring(url.indexOf(RESOURCES_FOLDER_NAME) + RESOURCES_FOLDER_NAME.length());
                    projectDto.getAdditionalImages().add(url);
                }
            }
        } catch (IOException exception) {
            throw new ProjectWasNotUpdatedException(ExceptionMessages.PROJECT_WAS_NOT_CREATED_IMAGES_ERROR);
        }
    }

    public void saveProjectImages(ProjectDto savedProject, MultipartFile[] files) {
        if (files.length > 15) {
            throw new ProjectWasNotUpdatedException("Nie możesz dodać więcej niż 15 zdjęć.");
        }
        try {
            String fileDirectoryName = FULL_PATH_TO_UPLOAD_DIRECTORY + savedProject.getId() + File.separator;
            Path dirPath = Paths.get(fileDirectoryName);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            for (int i = 0; i < files.length; i++) {
                Path fileNameAndPath = Paths.get(String.valueOf(dirPath), files[i].getOriginalFilename());
                if (!Objects.isNull(files[i]) && files[i].getOriginalFilename().length() > 3) {
                    Files.write(fileNameAndPath, files[i].getBytes());
                    String url = String.valueOf(fileNameAndPath);
                    url = url.substring(url.indexOf(RESOURCES_FOLDER_NAME) + RESOURCES_FOLDER_NAME.length());
                    if (i == 0) {
                        savedProject.setTitleImageLink(url);
                    } else {
                        savedProject.getAdditionalImages().add(url);
                    }
                }
            }
        } catch (IOException exception) {
            projectService.delete(savedProject.getId());
            throw new ProjectWasNotCreatedException(ExceptionMessages.PROJECT_WAS_NOT_CREATED_IMAGES_ERROR);
        }

    }
}
