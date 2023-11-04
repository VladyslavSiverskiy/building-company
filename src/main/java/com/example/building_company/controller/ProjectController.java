package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import com.example.building_company.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    public static String UPLOAD_DIRECTORY = "C:\\java_training\\spring\\building_company\\building-company\\pictures";
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
    ) throws IOException {
        for (int i = 0; i < files.length; i++) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, files[i].getOriginalFilename());
            if (!Objects.isNull(files[i]) && files[i].getOriginalFilename().length() > 3){
                Files.write(fileNameAndPath, files[i].getBytes());
                if(i == 0) {
                    project.setTitleImageLink(String.valueOf(fileNameAndPath));
                }else{
                    project.getAdditionalImages().add(String.valueOf(fileNameAndPath));
                }
            }
        }
        projectService.save(project);
        return "redirect:/";
    }

    @GetMapping("/{projectId}/delete")
    public String delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return "redirect:/admin/home";
    }

    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.findAll()
                .stream()
                .map(element -> modelMapper.map(element, Project.class))
                .collect(Collectors.toList()));
        return "";
    }
}
