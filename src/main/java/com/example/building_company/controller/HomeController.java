package com.example.building_company.controller;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final ProjectService projectService;

    @GetMapping
    public String openHomePage(Model model) {
        List<ProjectDto> projectDtoList = projectService.findAll();
        List<ProjectDto> sublist = projectDtoList.subList(Math.max(projectDtoList.size() - 3, 0), projectDtoList.size());
        model.addAttribute("recent_projects", sublist);
        return "index";
    }

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam String lang, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("lang", lang);
        return "redirect:/home";
    }
}
