package com.xyz.movieservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
}