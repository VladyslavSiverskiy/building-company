package com.example.building_company.mapping;

import org.modelmapper.AbstractConverter;

import com.example.building_company.dto.WorkDto;
import com.example.building_company.model.Work;

public class WorkMapper extends AbstractConverter<WorkDto, Work> {

    @Override
    protected Work convert(WorkDto workDto) {
        return Work.builder()
                .id(workDto.getId())
                .work(workDto.getWork())
                .workDescription(workDto.getWorkDescription())
                .build();
    }

}
