package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.dto.ReviewDto;
import com.example.building_company.dto.WorkDto;
import com.example.building_company.service.ProjectService;
import com.example.building_company.service.ReviewService;
import com.example.building_company.service.WorkService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final ProjectService projectService;
    private final ReviewService reviewService;
    private final WorkService workService;

    @GetMapping
    public String openHomePage(Model model) {
        List<ProjectDto> projectDtoList = projectService.findAll();
        List<ProjectDto> sublist = projectDtoList.subList(Math.max(projectDtoList.size() - 4, 0),
                projectDtoList.size());
        model.addAttribute("recent_projects", sublist);

        List<ReviewDto> reviewDtos = reviewService.findAll();
        model.addAttribute("recent_reviews", reviewDtos);

        List<WorkDto> workDtos = workService.findAll();
        model.addAttribute("recent_works", workDtos);
        return "index";
    }
}
