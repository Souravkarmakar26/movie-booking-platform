package com.xyz.movieservice.repository;

import com.xyz.movieservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowId(Long showId);
    List<Seat> findByShowIdAndSeatNumberIn(
            Long showId,
            List<String> seatNumbers
    );
}