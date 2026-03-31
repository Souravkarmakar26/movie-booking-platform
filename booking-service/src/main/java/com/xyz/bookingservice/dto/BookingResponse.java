package com.xyz.bookingservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookingResponse {

    private Long bookingId;

    private String status;

    private Double totalAmount;

}
