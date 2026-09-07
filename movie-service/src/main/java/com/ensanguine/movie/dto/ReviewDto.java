package com.ensanguine.movie.dto;

public record ReviewDto(Integer id,
                        Integer rating,
                        String comment,
                        String reviewer) {
}
