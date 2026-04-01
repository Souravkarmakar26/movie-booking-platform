package com.xyz.movieservice.kafka;

import com.xyz.movieservice.event.BookingCreatedEvent;
import com.xyz.movieservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final SeatService seatService;

    @KafkaListener(topics = "booking-created", groupId = "movie-service-v2", containerFactory = "kafkaListenerContainerFactory")
    public void handleBookingEvent(
            BookingCreatedEvent event){

        seatService.confirmBooking(
                event.getShowId(),
                event.getSeatNumbers()
        );
    }
}
