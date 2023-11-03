package com.example.building_company.mapping;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.model.Project;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoMapper extends AbstractConverter<ProjectDto, Project> {
    @Override
    protected Project convert(ProjectDto projectDto) {
        return null;
    }
}
