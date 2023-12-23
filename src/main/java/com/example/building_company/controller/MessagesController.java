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
    private final HomeController homeController;

    private final EmailServiceImpl emailService;

    @PostMapping
    public String sentToMe(@RequestParam(name = "phoneNumber") String phoneNumber, Model model) {
        String regex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
        String phoneNumberWithoutSymbols;
        phoneNumberWithoutSymbols = phoneNumber.replaceAll("\\D", "");
        if (phoneNumber.isEmpty() || !phoneNumberWithoutSymbols.matches(regex)) {
            model.addAttribute("errorMessage", "Nieprawidłowy format numeru telefonu!");
        }else {
            emailService.sendEmail("vitaliisachko188@gmail.com", "Cześć, Vitaliyu!", phoneNumber);
            model.addAttribute("successMessage", "Wiadomość została wysłana pomyślnie!");
        }   
        return homeController.openHomePage(model);
    }
}
