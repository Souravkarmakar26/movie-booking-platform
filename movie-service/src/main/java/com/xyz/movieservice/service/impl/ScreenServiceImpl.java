package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.ScreenRequest;
import com.xyz.movieservice.dto.ScreenResponse;
import com.xyz.movieservice.entity.Screen;
import com.xyz.movieservice.entity.Theatre;
import com.xyz.movieservice.repository.ScreenRepository;
import com.xyz.movieservice.repository.TheatreRepository;
import com.xyz.movieservice.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    @Override
    public ScreenResponse createScreen(ScreenRequest request) {

        Theatre theatre = theatreRepository.findById(request.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        Screen screen = Screen.builder()
                .name(request.getName())
                .totalSeats(request.getTotalSeats())
                .theatre(theatre)
                .build();

        Screen saved = screenRepository.save(screen);

        return ScreenResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .totalSeats(saved.getTotalSeats())
                .theatreId(saved.getTheatre().getId())
                .build();
    }

    @Override
    public List<ScreenResponse> getScreensByTheatre(Long theatreId) {

        return screenRepository.findByTheatreId(theatreId)
                .stream()
                .map(screen -> ScreenResponse.builder()
                        .id(screen.getId())
                        .name(screen.getName())
                        .totalSeats(screen.getTotalSeats())
                        .theatreId(screen.getTheatre().getId())
                        .build())
                .collect(Collectors.toList());
    }
}