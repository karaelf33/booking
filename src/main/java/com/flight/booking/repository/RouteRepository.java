package com.flight.booking.repository;

import com.flight.booking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Optional<Route> findByName(String routeName);
}
