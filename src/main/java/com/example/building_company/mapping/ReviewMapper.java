package com.example.building_company.mapping;

import org.modelmapper.AbstractConverter;

import com.example.building_company.dto.ReviewDto;
import com.example.building_company.model.Review;

public class ReviewMapper extends AbstractConverter<ReviewDto, Review> {

    @Override
    protected Review convert(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .author(reviewDto.getAuthor())
                .content(reviewDto.getContent())
                .creationTime(reviewDto.getCreationTime())
                .isVerified(reviewDto.getIsVerified())
                .build();
    }

}
