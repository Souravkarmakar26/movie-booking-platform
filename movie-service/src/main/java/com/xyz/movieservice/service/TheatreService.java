package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.TheatreRequest;
import com.xyz.movieservice.dto.TheatreResponse;

import java.util.List;

public interface TheatreService {

    TheatreResponse createTheatre(TheatreRequest request);

    List<TheatreResponse> getTheatres(String city);
}
