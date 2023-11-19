package com.example.building_company.mapping;

import com.example.building_company.dto.ReviewDto;
import com.example.building_company.model.Review;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoMapper extends AbstractConverter<Review, ReviewDto> {
    @Override
    protected ReviewDto convert(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getAuthor(),
                review.getContent(),
                review.getCreationTime(),
                review.getIsVerified());
    }
}
