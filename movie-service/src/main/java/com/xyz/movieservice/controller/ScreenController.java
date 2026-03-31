package com.xyz.movieservice.controller;

import com.xyz.movieservice.dto.ScreenRequest;
import com.xyz.movieservice.dto.ScreenResponse;
import com.xyz.movieservice.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping
    public ScreenResponse create(@RequestBody ScreenRequest request) {
        return screenService.createScreen(request);
    }

    @GetMapping("/{theatreId}")
    public List<ScreenResponse> getScreens(@PathVariable Long theatreId) {
        return screenService.getScreensByTheatre(theatreId);
    }
}