package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller

@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    public static String UPLOAD_DIRECTORY = "C:\\java_training\\spring\\building_company\\building-company\\pictures";

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

        for (MultipartFile file: files) {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }

        System.out.println(project);
        return "redirect:/";
    }

}
