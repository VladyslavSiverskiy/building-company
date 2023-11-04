package com.example.building_company.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.building_company.model.Project;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String category;
    private String clientName;
    private String description;
    private String place;
    private String titleImageLink;
    private List<String> additionalImages = new ArrayList<>();
}