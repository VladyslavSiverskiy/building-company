package com.example.building_company.service;

import java.util.List;

import com.example.building_company.dto.WorkDto;

public interface WorkService {
    WorkDto findById(Long workId);

    List<WorkDto> findAll();

    WorkDto save(WorkDto workDto);

    WorkDto update(WorkDto workDto);

    void delete(Long workId);
}
