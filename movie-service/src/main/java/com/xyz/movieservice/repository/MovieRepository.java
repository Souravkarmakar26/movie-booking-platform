package com.xyz.movieservice.repository;

import com.xyz.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
