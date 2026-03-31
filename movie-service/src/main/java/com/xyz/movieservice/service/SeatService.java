package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.SeatLockRequest;
import com.xyz.movieservice.dto.SeatResponse;
import com.xyz.movieservice.dto.SeatValidationRequest;

import java.util.List;

public interface SeatService {
    List<SeatResponse> getSeatsByShow(Long showId);

    void lockSeats(SeatLockRequest request);

    Boolean validateSeatLock(SeatValidationRequest request);
}
