package com.xyz.movieservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String genre;

    private String language;

    private Integer duration;
}
