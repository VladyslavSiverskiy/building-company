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

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final FileService fileService;
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
        fileService.saveProjectImages(savedProject, files);
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
        fileService.updateProjectImages(projectDto, projectDtoToUpdate, titleImage, files);
        projectDto.getAdditionalImages().addAll(projectDtoToUpdate.getAdditionalImages());
        projectService.update(projectDto);
        return "redirect:/admin";
    }
}
