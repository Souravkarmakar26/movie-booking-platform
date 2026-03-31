package com.xyz.bookingservice.repository;

import com.xyz.bookingservice.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepository
        extends JpaRepository<BookingSeat, Long> {

}
