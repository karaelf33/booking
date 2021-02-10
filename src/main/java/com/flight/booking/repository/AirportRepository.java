package com.flight.booking.repository;

import com.flight.booking.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Optional<Airport> findByName(String name);
}
