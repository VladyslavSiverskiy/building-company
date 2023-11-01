package com.example.building_company.dto;

import java.time.LocalDateTime;

import com.example.building_company.model.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private LocalDateTime creationTime;
    private Boolean isVerified;

    public static Review toEntity(ReviewDto dto) {
        return Review.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .creationTime(dto.getCreationTime())
                .isVerified(dto.getIsVerified())
                .build();
    }

    public static ReviewDto fromEntity(Review entity) {
        return new ReviewDto(
                entity.getId(),
                entity.getContent(),
                entity.getCreationTime(),
                entity.getIsVerified());
    }
}
