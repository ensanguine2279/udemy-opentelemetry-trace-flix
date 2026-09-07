package com.ensanguine.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensanguine.movie.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
