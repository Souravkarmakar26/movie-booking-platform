package com.xyz.bookingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowResponse {

    private Long id;

    private Double price;

    private LocalDateTime startTime;

}
