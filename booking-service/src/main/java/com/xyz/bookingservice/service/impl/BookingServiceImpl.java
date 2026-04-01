package com.xyz.bookingservice.service.impl;

import com.xyz.bookingservice.dto.BookingRequest;
import com.xyz.bookingservice.dto.BookingResponse;
import com.xyz.bookingservice.dto.SeatValidationRequest;
import com.xyz.bookingservice.dto.ShowResponse;
import com.xyz.bookingservice.entity.Booking;
import com.xyz.bookingservice.entity.BookingSeat;
import com.xyz.bookingservice.entity.BookingStatus;
import com.xyz.bookingservice.event.BookingCreatedEvent;
import com.xyz.bookingservice.kafka.BookingEventProducer;
import com.xyz.bookingservice.pricing.DiscountEngine;
import com.xyz.bookingservice.repository.BookingRepository;
import com.xyz.bookingservice.repository.BookingSeatRepository;
import com.xyz.bookingservice.service.BookingService;
import com.xyz.bookingservice.service.MovieServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final MovieServiceClient movieServiceClient;
    private final BookingEventProducer bookingEventProducer;
    private final DiscountEngine discountEngine;

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        log.info("creating booking for user : {}", request.getUserId());
        // Validate seat lock with Movie Service
        SeatValidationRequest validationRequest = new SeatValidationRequest();
        validationRequest.setShowId(request.getShowId());
        validationRequest.setUserId(request.getUserId());
        validationRequest.setSeatNumbers(request.getSeatNumbers());
        Boolean isValid =
                movieServiceClient.validateSeatLock(validationRequest);
        log.info("Seat is validated : {}",isValid.toString());
        if(!isValid){
            log.error("Seat lock expired or invalid");
            throw new RuntimeException(
                    "Seat lock expired or invalid"
            );
        }
        //Fetch show details from Movie Service
        ShowResponse show =
                movieServiceClient.getShow(request.getShowId());
        log.info("show details from movie service : {}",request.getShowId());
        //Calculate final price after discount if applied
        double totalAmount = calculateFinalPrice(request, show);
        //Create booking
        Booking booking = Booking.builder()
                .showId(request.getShowId())
                .userId(request.getUserId())
                .status(BookingStatus.CREATED)
                .totalAmount(totalAmount)
                .createdAt(LocalDateTime.now())
                .build();
        Booking savedBooking =
                bookingRepository.save(booking);
        //Save booking seats
        for(String seat : request.getSeatNumbers()) {
            BookingSeat bookingSeat = BookingSeat.builder()
                    .bookingId(savedBooking.getId())
                    .seatNumber(seat)
                    .build();
            bookingSeatRepository.save(bookingSeat);
        }
        BookingCreatedEvent event =
                new BookingCreatedEvent(
                        savedBooking.getId(),
                        request.getShowId(),
                        request.getSeatNumbers()
                );

        bookingEventProducer.publishBookingCreatedEvent(event);
        log.info("created booking for user : {} and booking id : {}", request.getUserId(), savedBooking.getId());
        return BookingResponse.builder()
                .bookingId(savedBooking.getId())
                .status(savedBooking.getStatus().name())
                .totalAmount(totalAmount)
                .build();
    }

    private double calculateFinalPrice(BookingRequest request, ShowResponse show) {
        int ticketCount = request.getSeatNumbers().size();

        double basePrice =
                show.getPrice() * ticketCount;

        return discountEngine.applyDiscounts(
                basePrice,
                ticketCount,
                show.getStartTime()
        );
    }
}
