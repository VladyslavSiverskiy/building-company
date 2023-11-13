package com.example.building_company.service;

import com.example.building_company.dto.ProjectDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void deleteImage(String pathInStaticFolder);

    void updateProjectImages(
            ProjectDto projectDto,
            ProjectDto projectDtoToUpdate,
            MultipartFile titleImage,
            MultipartFile[] files
    );

    void saveProjectImages(ProjectDto savedProject, MultipartFile[] files);
}
