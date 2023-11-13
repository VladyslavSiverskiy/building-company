package com.example.building_company.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    @GetMapping
    public String openHomePage() {
        return "index";
    }
}
