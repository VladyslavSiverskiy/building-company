package com.example.building_company.controller;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.building_company.dto.ReviewDto;
import com.example.building_company.service.ReviewServiceImpl;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final ModelMapper modelMapper;

    @GetMapping("/add-review")
    public String create(Model model) {
        model.addAttribute("review", new ReviewDto());
        return "add-review";
    }

    @PostMapping("/add-review")
    public String createReview(@ModelAttribute("reviewDto") ReviewDto reviewDto, Model model) {
        reviewDto.setCreationTime(LocalDate.now());
        ReviewDto createdReview = reviewService.save(reviewDto);
        model.addAttribute("createdReview", createdReview);
        return "redirect:/home";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editReview(@PathVariable Long id, Model model) {
        model.addAttribute("review", modelMapper.map(reviewService.findById(id), ReviewDto.class));
        return "edit-review";
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateReview(@ModelAttribute("review") ReviewDto reviewDto, @PathVariable Long id,
            @RequestParam(value = "isVerified", required = false) Boolean isVerified) {
        ReviewDto existingReview = reviewService.findById(id);

        existingReview.setContent(reviewDto.getContent());
        existingReview.setAuthor(reviewDto.getAuthor());
        existingReview.setCreationTime(existingReview.getCreationTime());
        existingReview.setIsVerified(isVerified != null && isVerified);

        reviewService.update(existingReview);
        return "redirect:/review/" + id + "/read";
    }

    @GetMapping("/{id}/read")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String readById(@PathVariable Long id, Model model) {
        model.addAttribute("review", modelMapper.map(reviewService.findById(id), ReviewDto.class));
        return "/review-info";
    }

    @GetMapping("/all-reviews")
    public String getAllReviews(Model model) {
        List<ReviewDto> reviews = reviewService.findAll();
        model.addAttribute("reviews", reviews);
        return "all-reviews";
    }

    @GetMapping("/{reviewId}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/admin";
    }
}
