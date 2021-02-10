package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO addAirport(@RequestParam String location,
                                 @RequestParam String name) {
        return airportService.addAirport(name, location);
    }

    @GetMapping(value = "/search/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO searchAirline(@PathVariable String name) {
        return airportService.searchAirport(name);
    }
}
