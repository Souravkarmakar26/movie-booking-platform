package com.xyz.bookingservice.service;

import com.xyz.bookingservice.dto.BookingRequest;
import com.xyz.bookingservice.dto.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(BookingRequest request);

}
