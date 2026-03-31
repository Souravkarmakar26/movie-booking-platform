package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.SeatResponse;

import java.util.List;

public interface SeatService {
    List<SeatResponse> getSeatsByShow(Long showId);
}
