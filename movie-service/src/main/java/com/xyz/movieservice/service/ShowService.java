package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.ShowRequest;
import com.xyz.movieservice.dto.ShowResponse;

import java.util.List;

public interface ShowService {

    ShowResponse createShow(ShowRequest request);

    List<ShowResponse> getShowsByMovie(Long movieId);

    ShowResponse getShow(Long showId);
}