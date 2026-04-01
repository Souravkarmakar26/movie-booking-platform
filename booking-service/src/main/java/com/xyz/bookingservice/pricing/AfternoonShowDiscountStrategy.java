package com.xyz.bookingservice.pricing;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AfternoonShowDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(
            double totalPrice,
            int numberOfTickets,
            LocalDateTime showTime
    ) {
        int hour = showTime.getHour();
        if (hour >= 12 && hour <= 16) {
            return totalPrice * 0.8;
        }
        return totalPrice;
    }
}