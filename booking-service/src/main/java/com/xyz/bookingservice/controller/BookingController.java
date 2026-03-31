package com.xyz.bookingservice.controller;

import com.xyz.bookingservice.dto.BookingRequest;
import com.xyz.bookingservice.dto.BookingResponse;
import com.xyz.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse createBooking(
            @RequestBody BookingRequest request){

        return bookingService.createBooking(request);
    }

}
