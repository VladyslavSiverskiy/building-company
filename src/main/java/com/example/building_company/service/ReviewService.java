package com.example.building_company.service;

import java.util.List;

import com.example.building_company.dto.ReviewDto;

public interface ReviewService {

    ReviewDto findById(Long reviewId);

    List<ReviewDto> findAll();

    ReviewDto save(ReviewDto reviewDto);

    ReviewDto update(ReviewDto reviewDto);

    void delete(Long reviewId);

}