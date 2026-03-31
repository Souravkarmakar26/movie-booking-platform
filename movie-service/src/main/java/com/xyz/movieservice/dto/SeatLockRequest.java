package com.xyz.movieservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatLockRequest {

    private Long showId;
    private List<String> seatNumbers;
    private String userId;
}
