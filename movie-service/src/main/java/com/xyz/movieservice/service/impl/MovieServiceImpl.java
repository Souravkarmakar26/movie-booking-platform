package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.MovieRequest;
import com.xyz.movieservice.dto.MovieResponse;
import com.xyz.movieservice.entity.Movie;
import com.xyz.movieservice.mapper.MovieMapper;
import com.xyz.movieservice.repository.MovieRepository;
import com.xyz.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponse createMovie(MovieRequest request) {

        Movie movie = movieMapper.toEntity(request);

        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.toResponse(savedMovie);
    }

    @Override
    public List<MovieResponse> getMovies() {

        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }
}
