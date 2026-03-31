package com.xyz.bookingservice.service.impl;

import com.xyz.bookingservice.dto.BookingRequest;
import com.xyz.bookingservice.dto.BookingResponse;
import com.xyz.bookingservice.dto.ShowResponse;
import com.xyz.bookingservice.entity.Booking;
import com.xyz.bookingservice.entity.BookingSeat;
import com.xyz.bookingservice.entity.BookingStatus;
import com.xyz.bookingservice.repository.BookingRepository;
import com.xyz.bookingservice.repository.BookingSeatRepository;
import com.xyz.bookingservice.service.BookingService;
import com.xyz.bookingservice.service.MovieServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final MovieServiceClient movieServiceClient;

    @Override
    public BookingResponse createBooking(BookingRequest request) {

        ShowResponse show =
                movieServiceClient.getShow(request.getShowId());

        double totalAmount =
                show.getPrice() * request.getSeatNumbers().size();

        Booking booking = Booking.builder()
                .showId(request.getShowId())
                .userId(request.getUserId())
                .status(BookingStatus.CREATED)
                .totalAmount(totalAmount)
                .createdAt(LocalDateTime.now())
                .build();

        Booking savedBooking =
                bookingRepository.save(booking);

        for (String seat : request.getSeatNumbers()) {

            BookingSeat bookingSeat = BookingSeat.builder()
                    .bookingId(savedBooking.getId())
                    .seatNumber(seat)
                    .build();

            bookingSeatRepository.save(bookingSeat);

        }
        return BookingResponse.builder()
                .bookingId(savedBooking.getId())
                .status(savedBooking.getStatus().name())
                .totalAmount(totalAmount)
                .build();
    }
}
