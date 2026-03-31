package com.xyz.movieservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowRequest {

    private Long movieId;

    private Long screenId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;
}