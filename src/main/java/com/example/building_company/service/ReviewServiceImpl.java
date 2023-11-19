package com.example.building_company.service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.building_company.constants.ExceptionMessages;
import com.example.building_company.dto.ReviewDto;
import com.example.building_company.exception.ReviewNotFoundException;
import com.example.building_company.model.Review;
import com.example.building_company.repository.ReviewRepository;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReviewDto findById(Long reviewId) {
        return modelMapper.map(
                reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new ReviewNotFoundException(
                                ExceptionMessages.PROJECT_NOT_FOUND + reviewId)),
                ReviewDto.class);
    }

    @Override
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = reviewRepository.save(modelMapper.map(reviewDto, Review.class));
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        Long reviewId = reviewDto.getId();

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setAuthor(reviewDto.getAuthor());
            existingReview.setContent(reviewDto.getContent());
            existingReview.setCreationTime(reviewDto.getCreationTime());
            existingReview.setIsVerified(reviewDto.getIsVerified());

            Review updatedReview = reviewRepository.save(existingReview);

            return modelMapper.map(updatedReview, ReviewDto.class);
        } else {
            throw new ReviewNotFoundException(
                    ExceptionMessages.PROJECT_NOT_FOUND + reviewId);
        }
    }

    @Override
    public void delete(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
