package com.ensanguine.review.mapper;

import com.ensanguine.review.dto.ReviewDto;
import com.ensanguine.review.entity.Review;

public class EntityDtoMapper {

    public static ReviewDto toDto(Review review){
        return new ReviewDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getReviewer()
        );
    }

}
