package com.example.building_company.controller;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.ProjectDto;
import com.example.building_company.exception.ProjectWasNotCreatedException;
import com.example.building_company.exception.ProjectWasNotUpdatedException;
import com.example.building_company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import static com.example.building_company.constants.AppConstants.FULL_PATH_TO_UPLOAD_DIRECTORY;
import static com.example.building_company.constants.AppConstants.RESOURCES_FOLDER_NAME;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ModelMapper modelMapper;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "add-project";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("project") ProjectDto project,
            @RequestParam("images") MultipartFile[] files,
            Model model
    ) {
        ProjectDto savedProject = projectService.save(project);
        try {
            String fileDirectoryName = FULL_PATH_TO_UPLOAD_DIRECTORY + savedProject.getId() + File.separator;
            Path dirPath = Paths.get(fileDirectoryName);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            for (int i = 0; i < files.length; i++) {
                Path fileNameAndPath = Paths.get(String.valueOf(dirPath), files[i].getOriginalFilename());
                if (!Objects.isNull(files[i]) && files[i].getOriginalFilename().length() > 3){
                    Files.write(fileNameAndPath, files[i].getBytes());
                    String url = String.valueOf(fileNameAndPath);
                    url = url.substring(url.indexOf(RESOURCES_FOLDER_NAME) + RESOURCES_FOLDER_NAME.length());
                    if(i == 0) {
                        savedProject.setTitleImageLink(url);
                    }else{
                        savedProject.getAdditionalImages().add(url);
                    }
                }
            }
        }catch (IOException exception) {
            projectService.delete(savedProject.getId());
            throw new ProjectWasNotCreatedException(ExceptionMessages.PROJECT_WAS_NOT_CREATED_IMAGES_ERROR);
        }

        projectService.update(savedProject);
        return "redirect:/";
    }

    @GetMapping("/{projectId}/delete")
    public String delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return "redirect:/admin";
    }

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model){
        projectService.findById(projectId);
        model.addAttribute("project", projectService.findById(projectId));
        return ""; //TODO: create page for project
    }

    @GetMapping("/{projectId}/update")
    public String updateProject(@PathVariable Long projectId, Model model) {
        ProjectDto projectDto = projectService.findById(projectId);
        model.addAttribute("project", projectDto);
        model.addAttribute("startDate",
                Date.from(projectDto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        model.addAttribute("endDate",
                Date.from(projectDto.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return "edit-project";
    }

    @PostMapping("/{projectId}/update")
    public String updateProject( @ModelAttribute("project") ProjectDto projectDto,
                                 @PathVariable Long projectId,
                                 @RequestParam("start-date") String startDate,
                                 @RequestParam("end-date") String endDate,
                                 @RequestParam(value = "images",  required = false) MultipartFile[] files,
                                 @RequestParam(value = "title-image", required = false) MultipartFile titleImage
    ) {
        ProjectDto projectDtoToUpdate = projectService.findById(projectId);

        projectDto.setId(projectId);
        projectDto.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        projectDto.setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (Objects.isNull(titleImage)) {
            projectDto.setTitleImageLink(projectDtoToUpdate.getTitleImageLink());
        }else {
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
            }catch (IOException ex) {
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
        }catch (IOException exception) {
            throw new ProjectWasNotUpdatedException(ExceptionMessages.PROJECT_WAS_NOT_CREATED_IMAGES_ERROR);
        }
        projectDto.getAdditionalImages().addAll(projectDtoToUpdate.getAdditionalImages());
        projectService.update(projectDto);
        return "redirect:/admin";
    }
}
