package com.xyz.movieservice.controller;

import com.xyz.movieservice.dto.SeatResponse;
import com.xyz.movieservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{showId}/seats")
    public List<SeatResponse> getSeats(@PathVariable Long showId) {
        return seatService.getSeatsByShow(showId);
    }
}
