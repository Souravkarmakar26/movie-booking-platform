package com.xyz.bookingservice.service;

import com.xyz.bookingservice.dto.SeatValidationRequest;
import com.xyz.bookingservice.dto.ShowResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceClient {

    @Value("${movie.service.url}")
    private String movieServiceUrl;

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "movieService", fallbackMethod = "fallbackGetShow")
    public ShowResponse getShow(Long showId) {
        String url = movieServiceUrl+"/shows/" + showId;
        log.info("calling movie service for show : {}",url);
        return restTemplate.getForObject(url, ShowResponse.class);
    }

    public ShowResponse fallbackGetShow(Long showId, Exception ex) {
        log.error("Movie service unavailable");
        throw new RuntimeException("Movie service unavailable");
    }
    @CircuitBreaker(name = "movieService", fallbackMethod = "fallbackValidateSeat")
    public Boolean validateSeatLock(SeatValidationRequest request){
        String url = movieServiceUrl+"/shows/validate-lock";
        log.info("calling movie service for validate seats : {}",url);
        return restTemplate.postForObject(
                url,
                request,
                Boolean.class
        );
    }

    public Boolean fallbackValidateSeat(SeatValidationRequest request, Exception ex) {
        log.error("Fallback for validateSeatLock triggered. Reason: {}", ex.getMessage());
        return false;
    }

}
