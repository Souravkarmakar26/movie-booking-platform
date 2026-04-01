package com.xyz.bookingservice.event;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedEvent {

    private Long bookingId;

    private Long showId;

    private List<String> seatNumbers;
}
