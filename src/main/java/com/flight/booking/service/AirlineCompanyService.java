package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

@Service
public interface AirlineCompanyService {
    GenericDTO addAirlineCompany(String name);

    GenericDTO searchAirline(String name);
}
