package com.xyz.bookingservice.pricing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountEngine {

    private final List<DiscountStrategy> strategies;

    public double applyDiscounts(
            double totalPrice,
            int tickets,
            LocalDateTime showTime
    ) {
        double price = totalPrice;
        for (DiscountStrategy strategy : strategies) {
            price = strategy.applyDiscount(
                    price,
                    tickets,
                    showTime
            );
        }
        return price;
    }
}