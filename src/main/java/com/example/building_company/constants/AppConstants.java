package com.example.building_company.constants;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class AppConstants {

    static {
        Path currentDirectoryPath = FileSystems.getDefault().getPath("");
        ROOT_FOLDER = currentDirectoryPath.toAbsolutePath().toString();
    }

    private static final String ROOT_FOLDER;
    public static final String FULL_PATH_TO_UPLOAD_DIRECTORY =
            ROOT_FOLDER + File.separator + "target" + File.separator +
            "classes" + File.separator + "static" + File.separator + "pictures" + File.separator + "id";

    public static final String PATH_TO_STATIC_DIRECTORY =
            ROOT_FOLDER + File.separator + "target" + File.separator +
            "classes" + File.separator + "static";

    public static final String RESOURCES_FOLDER_NAME = "static";

    private AppConstants() {
    }
}
