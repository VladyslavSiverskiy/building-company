package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import com.example.building_company.service.FileService;
import com.example.building_company.service.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final ProjectService projectService;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String openAdminPage(@RequestParam(defaultValue = "project") String currentPage, Model model) {
        var pr = projectService.findAll()
                .stream()
                .map(element -> modelMapper.map(element, Project.class))
                .toList();
        model.addAttribute("projects", pr);
        model.addAttribute("currentPage", currentPage);
        return "admin-home";
    }

    @GetMapping("/project/deleteImage")
    public String deleteProjectImage(
            @RequestParam(name = "deleteImagePath", defaultValue = "title") String deleteImagePath,
            @RequestParam(name = "projectId") Long projectId
    ) {

        ProjectDto projectDto = projectService.findById(projectId);
        if (deleteImagePath.equals("title")) {
            fileService.deleteImage(projectDto.getTitleImageLink());
            projectDto.setTitleImageLink(null);
        }else {
            fileService.deleteImage(deleteImagePath);
            projectDto.getAdditionalImages().remove(deleteImagePath);
        }
        projectService.update(projectDto);
        return "redirect:/project/" + projectId + "/update";
    }

}
