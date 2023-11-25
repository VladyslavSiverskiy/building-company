package com.example.building_company.service;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.ProjectDto;
import com.example.building_company.exception.ProjectNotFoundException;
import com.example.building_company.model.Project;
import com.example.building_company.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectDto findById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(ExceptionMessages.PROJECT_NOT_FOUND + projectId));
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream()
                .map(element -> modelMapper.map(element, ProjectDto.class))
                .toList();
    }

    @Override
    public void delete(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(ExceptionMessages.PROJECT_NOT_FOUND + projectId));
        projectRepository.delete(project);
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        if (Objects.isNull(projectDto)) {
            throw new IllegalArgumentException("Project can`t be null.");
        }
        Project project = projectRepository.save(modelMapper.map(projectDto, Project.class));
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        if (Objects.isNull(projectDto)) {
            throw new IllegalArgumentException("Project can`t be null.");
        }
        Optional<Project> projectOptional = projectRepository.findById(projectDto.getId());
        if (projectOptional.isEmpty()) {
            throw new ProjectNotFoundException(ExceptionMessages.PROJECT_NOT_FOUND + projectDto.getId());
        }
        Project project = projectRepository.save(modelMapper.map(projectDto, Project.class));
        return modelMapper.map(project, ProjectDto.class);
    }

    public String extractFirstSentence(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String[] sentences = text.split("[.!?]");
        return sentences[0].trim();
    }
}
