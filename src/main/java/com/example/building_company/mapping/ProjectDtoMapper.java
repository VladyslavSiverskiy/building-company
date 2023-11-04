package com.example.building_company.mapping;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoMapper extends AbstractConverter<Project, ProjectDto>{

    @Override
    public ProjectDto convert(Project entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .category(entity.getCategory())
                .clientName(entity.getClientName())
                .description(entity.getDescription())
                .place(entity.getPlace())
                .titleImageLink(entity.getTitleImageLink())
                .additionalImages(entity.getAdditionalImages())
                .build();
    }
}
