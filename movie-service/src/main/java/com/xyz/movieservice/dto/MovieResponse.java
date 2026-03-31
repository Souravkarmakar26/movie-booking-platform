package com.xyz.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResponse {

    private Long id;
    private String title;
    private String genre;
    private String language;
    private Integer duration;
}
