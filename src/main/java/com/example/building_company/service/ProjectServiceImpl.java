package com.example.building_company.service;

import com.example.building_company.dto.ProjectDto;
import com.example.building_company.mapping.ProjectDtoMapper;
import com.example.building_company.model.Project;
import com.example.building_company.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectDtoMapper mapper;

    @Override
    public ProjectDto findById(Long projectId) {
        return null;
    }

    @Override
    public List<ProjectDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long projectId) {

    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project saved = projectRepository.save(mapper.convertToEntity(projectDto));
        return mapper.convertToDto(saved);
    }
}
