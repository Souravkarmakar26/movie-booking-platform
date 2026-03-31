package com.xyz.movieservice.controller;

import com.xyz.movieservice.dto.ShowRequest;
import com.xyz.movieservice.dto.ShowResponse;
import com.xyz.movieservice.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ShowResponse createShow(@RequestBody ShowRequest request) {
        return showService.createShow(request);
    }

    @GetMapping("/movie/{movieId}")
    public List<ShowResponse> getShows(@PathVariable Long movieId) {
        return showService.getShowsByMovie(movieId);
    }
}