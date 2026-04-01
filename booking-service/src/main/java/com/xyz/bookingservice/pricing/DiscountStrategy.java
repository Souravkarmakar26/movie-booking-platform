package com.xyz.bookingservice.pricing;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    double applyDiscount(
            double totalPrice,
            int numberOfTickets,
            LocalDateTime showTime
    );
}