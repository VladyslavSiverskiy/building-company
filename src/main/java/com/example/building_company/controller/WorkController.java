package com.example.building_company.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.building_company.dto.WorkDto;
import com.example.building_company.service.WorkService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final ModelMapper modelMapper;

    @GetMapping("/add-work")
    public String create(Model model) {
        model.addAttribute("work", new WorkDto());
        return "add-work";
    }

    @PostMapping("/add-work")
    public String createWork(@ModelAttribute("workDto") WorkDto workDto, Model model) {
        WorkDto createdWork = workService.save(workDto);
        model.addAttribute("createdWork", createdWork);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateWork(@PathVariable Long id, Model model) {
        model.addAttribute("work", modelMapper.map(workService.findById(id), WorkDto.class));
        return "edit-work";
    }

    @PostMapping("/{id}/update")
    public String updateWork(@ModelAttribute("work") WorkDto workDto, @PathVariable Long id) {
        WorkDto existingWork = workService.findById(id);
        existingWork.setWork(workDto.getWork());
        existingWork.setWorkDescription(workDto.getWorkDescription());

        workService.update(existingWork);
        return "redirect:/work/" + id + "/read";
    }

    @GetMapping("/{id}/read")
    public String readById(@PathVariable Long id, Model model) {
        model.addAttribute("work", modelMapper.map(workService.findById(id), WorkDto.class));
        return "work-info";
    }

    @GetMapping("/{id}/delete")
    public String deleteWork(@PathVariable Long id) {
        workService.delete(id);
        return "redirect:/admin";
    }
}
