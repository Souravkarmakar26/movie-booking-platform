package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.SeatResponse;
import com.xyz.movieservice.repository.SeatRepository;
import com.xyz.movieservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public List<SeatResponse> getSeatsByShow(Long showId) {

        return seatRepository.findByShowId(showId)
                .stream()
                .map(seat -> SeatResponse.builder()
                        .id(seat.getId())
                        .seatNumber(seat.getSeatNumber())
                        .status(seat.getStatus().name())
                        .build())
                .toList();
    }
}
