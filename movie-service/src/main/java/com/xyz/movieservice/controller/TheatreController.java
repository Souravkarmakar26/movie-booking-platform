package com.xyz.movieservice.controller;

import com.xyz.movieservice.dto.TheatreRequest;
import com.xyz.movieservice.dto.TheatreResponse;
import com.xyz.movieservice.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    public TheatreResponse create(@RequestBody TheatreRequest request) {
        return theatreService.createTheatre(request);
    }

    @GetMapping
    public List<TheatreResponse> get(@RequestParam(required = false) String city) {
        return theatreService.getTheatres(city);
    }
}
