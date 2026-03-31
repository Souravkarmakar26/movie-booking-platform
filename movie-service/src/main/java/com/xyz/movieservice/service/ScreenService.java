package com.xyz.movieservice.service;

import com.xyz.movieservice.dto.ScreenRequest;
import com.xyz.movieservice.dto.ScreenResponse;

import java.util.List;

public interface ScreenService {

    ScreenResponse createScreen(ScreenRequest request);

    List<ScreenResponse> getScreensByTheatre(Long theatreId);
}