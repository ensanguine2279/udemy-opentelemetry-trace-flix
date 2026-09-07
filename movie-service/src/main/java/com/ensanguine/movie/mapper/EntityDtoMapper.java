package com.ensanguine.movie.mapper;


import java.util.List;

import com.ensanguine.movie.dto.ActorDto;
import com.ensanguine.movie.dto.MovieDto;
import com.ensanguine.movie.dto.ReviewDto;
import com.ensanguine.movie.entity.Movie;

public class EntityDtoMapper {

    public static MovieDto toDto(Movie movie, List<ActorDto> actors, List<ReviewDto> reviews){
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                actors,
                reviews
        );
    }

}
