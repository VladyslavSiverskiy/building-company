package com.example.building_company.mapping;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoMapper{

    public Project convertToEntity(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .title(projectDto.getTitle())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .category(projectDto.getCategory())
                .clientName(projectDto.getClientName())
                .description(projectDto.getDescription())
                .place(projectDto.getPlace())
                .titleImageLink(projectDto.getTitleImageLink())
                .additionalImages(projectDto.getAdditionalImages())
                .build();
    }

    public ProjectDto convertToDto(Project entity) {
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
