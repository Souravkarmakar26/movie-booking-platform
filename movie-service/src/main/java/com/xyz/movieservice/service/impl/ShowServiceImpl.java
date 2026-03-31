package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.ShowRequest;
import com.xyz.movieservice.dto.ShowResponse;
import com.xyz.movieservice.entity.*;
import com.xyz.movieservice.repository.MovieRepository;
import com.xyz.movieservice.repository.ScreenRepository;
import com.xyz.movieservice.repository.SeatRepository;
import com.xyz.movieservice.repository.ShowRepository;
import com.xyz.movieservice.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;

    @Override
    public ShowResponse createShow(ShowRequest request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        Show show = Show.builder()
                .movie(movie)
                .screen(screen)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .price(request.getPrice())
                .build();

        Show saved = showRepository.save(show);

        seatGeneration(screen, saved);

        return ShowResponse.builder()
                .id(saved.getId())
                .movieId(movie.getId())
                .screenId(screen.getId())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .price(saved.getPrice())
                .build();
    }

    @Override
    public List<ShowResponse> getShowsByMovie(Long movieId) {

        return showRepository.findByMovieId(movieId)
                .stream()
                .map(show -> ShowResponse.builder()
                        .id(show.getId())
                        .movieId(show.getMovie().getId())
                        .screenId(show.getScreen().getId())
                        .startTime(show.getStartTime())
                        .endTime(show.getEndTime())
                        .price(show.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    private void seatGeneration(Screen screen, Show saved) {
        List<Seat> seats = new ArrayList<>();

        int totalSeats = screen.getTotalSeats();

        for (int i = 1; i <= totalSeats; i++) {

            Seat seat = Seat.builder()
                    .seatNumber("S" + i)
                    .status(SeatStatus.AVAILABLE)
                    .show(saved)
                    .build();

            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }
}