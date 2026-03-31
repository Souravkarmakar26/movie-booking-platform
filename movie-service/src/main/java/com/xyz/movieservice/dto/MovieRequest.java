package com.xyz.movieservice.dto;

import lombok.Data;

@Data
public class MovieRequest {

    private String title;

    private String genre;

    private String language;

    private Integer duration;
}
