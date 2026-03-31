package com.xyz.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SeatResponse {

    private Long id;
    private String seatNumber;
    private String status;

}