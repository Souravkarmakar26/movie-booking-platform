package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.SeatLockRequest;
import com.xyz.movieservice.dto.SeatResponse;
import com.xyz.movieservice.repository.SeatRepository;
import com.xyz.movieservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    private final RedisTemplate<String, Object> redisTemplate;

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

    @Override
    public void lockSeats(SeatLockRequest request) {

        for(String seat : request.getSeatNumbers()) {

            String key = "seat_lock:" + request.getShowId() + ":" + seat;

            Boolean isLocked = redisTemplate.opsForValue()
                    .setIfAbsent(key, request.getUserId(), Duration.ofMinutes(5));

            if(Boolean.FALSE.equals(isLocked)) {
                throw new RuntimeException("Seat already locked: " + seat);
            }
        }
    }
}
