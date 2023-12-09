package com.example.building_company.controller;

import com.example.building_company.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessagesController {

    private final EmailServiceImpl emailService;

    @GetMapping
    public void sentToMe(@RequestParam(name = "number") String phoneNumber) {
        emailService.sendEmail("v.siverskiy@gmail.com", "hello gkgkllfllf", phoneNumber);
    }
}
