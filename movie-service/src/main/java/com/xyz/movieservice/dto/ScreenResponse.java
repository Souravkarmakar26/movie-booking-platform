package com.xyz.movieservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ScreenResponse {

    private Long id;

    private String name;

    private Integer totalSeats;

    private Long theatreId;
}