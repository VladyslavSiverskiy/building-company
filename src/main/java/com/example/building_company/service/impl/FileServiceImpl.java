package com.example.building_company.service.impl;

import com.example.building_company.service.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.building_company.constants.AppConstants.PATH_TO_STATIC_DIRECTORY;
import static com.example.building_company.constants.ExceptionMessages.ERROR_WHILE_DELETING_FILE;

@Service
public class FileServiceImpl implements FileService {

    /**
     * @param pathInStaticFolder - path to image in static folder
     *                           (for ex: /pictures/id7/image.png)
     */
    public void deleteImage(String pathInStaticFolder) {
        Path path
                = Paths.get(PATH_TO_STATIC_DIRECTORY, pathInStaticFolder);
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(ERROR_WHILE_DELETING_FILE + e.getMessage());
        }
    }
}
