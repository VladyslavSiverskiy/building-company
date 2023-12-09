package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.service.FileService;
import com.example.building_company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.building_company.constants.AppConstants.DATE_FORMAT;


@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "all-projects";
    }

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
        if (project.getStartDate() != null && project.getEndDate() != null
            && (project.getStartDate().isAfter(project.getEndDate()))) {
                model.addAttribute("error", "Date of ending couldn't be less than date of beginning.");
                project.setStartDate(null);
                project.setEndDate(null);
                model.addAttribute("project", project);
                return "add-project";
        }
        try {
            ProjectDto savedProject = projectService.save(project);
            fileService.saveProjectImages(savedProject, files);
            projectService.update(savedProject);
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "add-project";
        }
        return "redirect:/admin";
    }

    @GetMapping("/{projectId}/delete")
    public String delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return "redirect:/admin";
    }

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model){
        ProjectDto projectDto = projectService.findById(projectId);
        model.addAttribute("project", projectDto);
        model.addAttribute("first_sentence", projectService.extractFirstSentence(projectDto.getDescription()));
        return "project";
    }

    @GetMapping("/{projectId}/next")
    public String nextProject(@PathVariable Long projectId) {
        List<ProjectDto> allProjects = projectService.findAll();   // (id = 28, id = 29, id = 31)   i = 1
        Long idOfNext = allProjects.get(0).getId();
        for (int i = 0; i < allProjects.size(); i++) {
            if (Objects.equals(allProjects.get(i).getId(), projectId)) {
                if (i >= allProjects.size() - 1) {
                    idOfNext = allProjects.get(0).getId();
                }else{
                    idOfNext = allProjects.get(i+1).getId();
                }
            }
        }
        return "redirect:/project/" + idOfNext;
    }


    @GetMapping("/{projectId}/prev")
    public String prevProject(@PathVariable Long projectId) {
        List<ProjectDto> allProjects = projectService.findAll();   // (id = 28, id = 29, id = 31)   i = 1
        Long idOfPrev = allProjects.get(0).getId();
        for (int i = 0; i < allProjects.size(); i++) {
            if (Objects.equals(allProjects.get(i).getId(), projectId)) {
                if (i == 0) {
                    idOfPrev = allProjects.get(allProjects.size() - 1).getId();
                }else{
                    idOfPrev = allProjects.get(i-1).getId();
                }
            }
        }
        return "redirect:/project/" + idOfPrev;
    }

    @GetMapping("/{projectId}/update")
    public String updateProject(@PathVariable Long projectId, Model model) {
        ProjectDto projectDto = projectService.findById(projectId);
        model.addAttribute("project", projectDto);
        if (projectDto.getStartDate() != null) {
            model.addAttribute("startDate",
                    Date.from(projectDto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
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
                                 @RequestParam(value = "title-image", required = false) MultipartFile titleImage,
                                 Model model
    ) {
        ProjectDto projectDtoToUpdate = projectService.findById(projectId);
        projectDto.setId(projectId);
        if(!startDate.isEmpty() && (LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DATE_FORMAT))
                    .isAfter(LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DATE_FORMAT))))) {
                model.addAttribute("error", "Date of ending couldn't be less than date of beginning.");
                model.addAttribute("project", projectDtoToUpdate);
                model.addAttribute("startDate",
                        Date.from(projectDtoToUpdate.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                model.addAttribute("endDate",
                        Date.from(projectDtoToUpdate.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                return "edit-project";
        }
        if (!startDate.isEmpty()) {
            projectDto.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DATE_FORMAT)));
        }
        projectDto.setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DATE_FORMAT)));
        fileService.updateProjectImages(projectDto, projectDtoToUpdate, titleImage, files);
        projectDto.getAdditionalImages().addAll(projectDtoToUpdate.getAdditionalImages());
        try {
            projectService.update(projectDto);
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("project", projectDtoToUpdate);
            if (projectDtoToUpdate.getStartDate() != null) {
                model.addAttribute("startDate",
                        Date.from(projectDtoToUpdate.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            model.addAttribute("endDate",
                    Date.from(projectDtoToUpdate.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            return "edit-project";
        }
        return "redirect:/admin";
    }
}
