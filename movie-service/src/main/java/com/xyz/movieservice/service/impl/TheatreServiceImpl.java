package com.xyz.movieservice.service.impl;

import com.xyz.movieservice.dto.TheatreRequest;
import com.xyz.movieservice.dto.TheatreResponse;
import com.xyz.movieservice.entity.Theatre;
import com.xyz.movieservice.repository.TheatreRepository;
import com.xyz.movieservice.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    @Override
    public TheatreResponse createTheatre(TheatreRequest request) {

        Theatre theatre = Theatre.builder()
                .name(request.getName())
                .city(request.getCity())
                .address(request.getAddress())
                .partnerId(request.getPartnerId())
                .build();

        Theatre saved = theatreRepository.save(theatre);

        return TheatreResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .city(saved.getCity())
                .address(saved.getAddress())
                .partnerId(saved.getPartnerId())
                .build();
    }

    @Override
    public List<TheatreResponse> getTheatres(String city) {

        List<Theatre> theatres;

        if (city != null) {
            theatres = theatreRepository.findByCity(city);
        } else {
            theatres = theatreRepository.findAll();
        }

        return theatres.stream()
                .map(t -> TheatreResponse.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .city(t.getCity())
                        .address(t.getAddress())
                        .partnerId(t.getPartnerId())
                        .build())
                .collect(Collectors.toList());
    }
}