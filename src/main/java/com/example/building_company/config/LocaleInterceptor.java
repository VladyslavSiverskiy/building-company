package com.example.building_company.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("lang");
        if (lang != null && !lang.isEmpty()) {
            request.setAttribute("lang", lang);
        } else {
            // Set a default locale if language is not present in the session
            request.setAttribute("lang", "pl"); // Default to Polish if not specified
        }
        return true;
    }
}