package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

@Service
public interface AirlineCompanyAirportsService {
    GenericDTO addAirlineCompanyAirportsRelation(String airlineCompanyName, String airportName);
}
