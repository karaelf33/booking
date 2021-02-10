package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface RouteService {

    GenericDTO addRoute(Date departureDate,
                        Date landingDate,
                        String routeName,
                        String departureAirportName,
                        String landingAirportName);

    GenericDTO searchRoute(String name);
}
