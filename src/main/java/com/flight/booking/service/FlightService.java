package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

@Service
public interface FlightService {
    GenericDTO createFlight(String code, int quota, String airlineCompanyName, String routeName,int initialPrice);

    GenericDTO searchFlight(String code);
}
