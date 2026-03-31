package com.xyz.movieservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenRequest {

    private String name;

    private Integer totalSeats;

    private Long theatreId;
}