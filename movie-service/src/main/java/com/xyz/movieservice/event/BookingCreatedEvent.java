package com.xyz.movieservice.event;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreatedEvent {

    private Long bookingId;
    private Long showId;
    private List<String> seatNumbers;
}
