package com.example.building_company.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.building_company.model.Project;

import lombok.*;

@Getter
@Setter
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
    private List<String> additionalImages;

    public static Project toEntity(ProjectDto dto) {
        return Project.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .category(dto.getCategory())
                .clientName(dto.getClientName())
                .description(dto.getDescription())
                .place(dto.getPlace())
                .titleImageLink(dto.getTitleImageLink())
                .additionalImages(dto.getAdditionalImages())
                .build();
    }

    public static ProjectDto fromEntity(Project entity) {
        return new ProjectDto(
                entity.getId(),
                entity.getTitle(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCategory(),
                entity.getClientName(),
                entity.getDescription(),
                entity.getPlace(),
                entity.getTitleImageLink(),
                entity.getAdditionalImages());
    }
}