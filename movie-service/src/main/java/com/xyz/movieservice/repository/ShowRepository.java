package com.xyz.movieservice.repository;

import com.xyz.movieservice.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);

    List<Show> findByScreenId(Long screenId);
}
