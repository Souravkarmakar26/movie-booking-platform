package com.xyz.movieservice.controller;

import com.xyz.movieservice.dto.MovieRequest;
import com.xyz.movieservice.dto.MovieResponse;
import com.xyz.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public MovieResponse createMovie(@RequestBody MovieRequest request){
        return movieService.createMovie(request);
    }

    @GetMapping
    public List<MovieResponse> getMovies(){
        return movieService.getMovies();
    }
}
