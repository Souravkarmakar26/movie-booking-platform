package com.xyz.bookingservice.service;

import com.xyz.bookingservice.dto.ShowResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ShowResponse getShow(Long showId) {

        String url = "http://localhost:8081/shows/" + showId;

        return restTemplate.getForObject(url, ShowResponse.class);

    }
}
