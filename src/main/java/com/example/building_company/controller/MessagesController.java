package com.example.building_company.controller;

import com.example.building_company.service.impl.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/message")
public class MessagesController {

    private final EmailServiceImpl emailService;

    @PostMapping
    public String sentToMe(@RequestParam(name = "phoneNumber") String phoneNumber, Model model) {
        if (phoneNumber.isEmpty()) {
            model.addAttribute("errorMessage", "Podaj numer telefonu!");
            return "index";
        }
        emailService.sendEmail("rksvjdbx@gmail.com", "Hello, Nazar!", phoneNumber);

        return "redirect:/home";
    }
}
