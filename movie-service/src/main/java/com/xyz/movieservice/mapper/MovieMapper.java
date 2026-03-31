package com.xyz.movieservice.mapper;

import com.xyz.movieservice.dto.MovieRequest;
import com.xyz.movieservice.dto.MovieResponse;
import com.xyz.movieservice.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequest request){
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());
        movie.setLanguage(request.getLanguage());
        movie.setDuration(request.getDuration());
        return movie;
    }

    public MovieResponse toResponse(Movie movie){
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getDuration()
        );
    }
}
