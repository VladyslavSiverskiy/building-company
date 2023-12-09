package com.example.building_company.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
    private String clientName;
    private String description;
    private String place;
    private String titleImageLink;
    private List<String> additionalImages = new ArrayList<>();
}