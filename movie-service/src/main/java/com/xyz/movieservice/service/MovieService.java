package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.MovieRequest;
import com.xyz.movieservice.dto.MovieResponse;

import java.util.List;

public interface MovieService {
    MovieResponse createMovie(MovieRequest request);

    List<MovieResponse> getMovies();
}
