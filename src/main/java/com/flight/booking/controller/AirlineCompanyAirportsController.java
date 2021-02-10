package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.AirlineCompanyAirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/airlineAirportsRelation")
public class AirlineCompanyAirportsController {

    @Autowired
    AirlineCompanyAirportsService airlineCompanyAirportsService;

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO addAirline(@RequestParam String airlineCompanyName,
                                 @RequestParam String airportName) {
        return airlineCompanyAirportsService.addAirlineCompanyAirportsRelation(airlineCompanyName, airportName);
    }

}
