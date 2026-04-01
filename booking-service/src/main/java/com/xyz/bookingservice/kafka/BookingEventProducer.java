package com.xyz.bookingservice.kafka;

import com.xyz.bookingservice.event.BookingCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingEventProducer {

    private final KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate;

    public void publishBookingCreatedEvent(
            BookingCreatedEvent event){

        kafkaTemplate.send("booking-created", event);
    }
}
