package com.example.building_company.mapping;

import com.example.building_company.dto.ReviewDto;
import com.example.building_company.model.Review;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoMapper extends AbstractConverter<ReviewDto, Review> {
    @Override
    protected Review convert(ReviewDto reviewDto) {
        return null;
    }
}
