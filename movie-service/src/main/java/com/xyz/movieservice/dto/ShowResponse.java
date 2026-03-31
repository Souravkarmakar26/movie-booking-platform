package com.xyz.movieservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ShowResponse {

    private Long id;

    private Long movieId;

    private Long screenId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;
}