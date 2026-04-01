package com.xyz.bookingservice.pricing;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ThirdTicketDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(
            double totalPrice,
            int numberOfTickets,
            LocalDateTime showTime
    )
    {
        if (numberOfTickets >= 3) {
            double pricePerTicket = totalPrice / numberOfTickets;
            double discount = pricePerTicket * 0.5;
            return totalPrice - discount;
        }
        return totalPrice;
    }
}