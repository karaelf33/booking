package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.Airport;
import com.flight.booking.repository.AirportRepository;
import com.flight.booking.utils.OperationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AirportServiceImplTest {

    AirportRepository airportRepository = mock(AirportRepository.class);
    AirportServiceImpl airportServiceImpl;

    String givenName= "IGA";
    String givenLocation = "Istanbul";

    @BeforeEach
    void setUp() {

        when(airportRepository.save(any())).thenReturn(null);
        airportServiceImpl = new AirportServiceImpl(airportRepository);

    }


    @Test
    void testMethod() {

        Airport testAirport = Airport.buildAirport(givenName, givenLocation);

        GenericDTO genericDTO = airportServiceImpl.addAirport(testAirport.getName(), testAirport.getLocation());

        HashMap<String,String> resultHashMap = (HashMap<String,String>) genericDTO.getResultData();
        String locationName = resultHashMap.get("location");
        assertEquals(locationName,givenLocation);

    }

}