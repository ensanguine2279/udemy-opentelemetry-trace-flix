package com.ensanguine.review.service;

import org.springframework.stereotype.Service;

import com.ensanguine.review.dto.ReviewDto;
import com.ensanguine.review.mapper.EntityDtoMapper;
import com.ensanguine.review.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDto> getReviews(Integer movieId) {
        return this.reviewRepository.findByMovieId(movieId)
                                    .stream()
                                    .map(EntityDtoMapper::toDto)
                                    .toList();
    }

}
