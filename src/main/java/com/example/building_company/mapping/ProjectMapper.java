package com.example.building_company.mapping;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper extends AbstractConverter<ProjectDto, Project> {

    @Override
    protected Project convert(ProjectDto projectDto) {
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
}
