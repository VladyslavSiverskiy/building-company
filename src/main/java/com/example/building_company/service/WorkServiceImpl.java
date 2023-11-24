package com.example.building_company.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.building_company.dto.WorkDto;
import com.example.building_company.exception.WorkNotFoundException;
import com.example.building_company.model.Work;
import com.example.building_company.repository.WorkRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final ModelMapper modelMapper;

    @Override
    public WorkDto findById(Long workId) {
        return modelMapper.map(
                workRepository.findById(workId).orElseThrow(() -> new WorkNotFoundException("Work not fount")),
                WorkDto.class);
    }

    @Override
    public List<WorkDto> findAll() {
        return workRepository.findAll().stream()
                .map(work -> modelMapper.map(work, WorkDto.class))
                .toList();
    }

    @Override
    public WorkDto save(WorkDto workDto) {
        Work work = workRepository.save(modelMapper.map(workDto, Work.class));
        return modelMapper.map(work, WorkDto.class);
    }

    @Override
    public WorkDto update(WorkDto workDto) {
        Long workId = workDto.getId();

        Optional<Work> optionalWork = workRepository.findById(workId);

        if (optionalWork.isPresent()) {
            Work existingWork = optionalWork.get();
            existingWork.setWork(workDto.getWork());
            existingWork.setWorkDescription(workDto.getWorkDescription());

            Work updatedWork = workRepository.save(existingWork);

            return modelMapper.map(updatedWork, WorkDto.class);
        } else {
            throw new WorkNotFoundException("Work not fount");
        }
    }

    @Override
    public void delete(Long workId) {
        workRepository.deleteById(workId);
    }

}
