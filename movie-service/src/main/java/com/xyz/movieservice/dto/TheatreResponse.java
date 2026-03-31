package com.xyz.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TheatreResponse {

    private Long id;

    private String name;

    private String city;

    private String address;

    private String partnerId;
}
