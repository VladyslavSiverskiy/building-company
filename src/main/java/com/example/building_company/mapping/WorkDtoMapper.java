package com.example.building_company.mapping;

import org.modelmapper.AbstractConverter;

import com.example.building_company.dto.WorkDto;
import com.example.building_company.model.Work;

public class WorkDtoMapper extends AbstractConverter<Work, WorkDto> {
    @Override
    protected WorkDto convert(Work work) {
        return new WorkDto(
                work.getId(),
                work.getWork(),
                work.getWorkDescription());
    }
}