package com.xyz.bookingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatValidationRequest {

    private Long showId;

    private String userId;

    private List    <String> seatNumbers;

}
