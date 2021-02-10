package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

@Service
public interface AirportService {
    GenericDTO addAirport(String location, String name);

    GenericDTO searchAirport(String name);
}
