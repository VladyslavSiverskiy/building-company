package com.example.building_company.auth;

import com.example.building_company.model.Role;
import com.example.building_company.model.User;
import com.example.building_company.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String authenticate() {
        return "signin";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "signin";
    }

    @GetMapping("/register")
    public String registerUser() {
        userService.save(new User(0L, "admin@gmail.com", "bc_admin", "huMAN!Q?YJsjWSdA", Role.ADMIN));
        return "signin";
    }
}
