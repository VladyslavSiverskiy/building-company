package com.example.building_company.service;

import com.example.building_company.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    ProjectDto findById(Long projectId);

    List<ProjectDto> findAll();

    void delete(Long projectId);

    ProjectDto save(ProjectDto projectDto);

    ProjectDto update(ProjectDto projectDto);

    public String extractFirstSentence(String text);
}
