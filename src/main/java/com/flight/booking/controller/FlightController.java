package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO createFlight(@RequestParam String code,
                                   @RequestParam int quota,
                                   @RequestParam String airlineCompanyName,
                                   @RequestParam String routeName,
                                   @RequestParam int initialPrice) {
        return flightService.createFlight(code, quota, airlineCompanyName, routeName, initialPrice);

    }

    @GetMapping(value = "/search/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO searchFlight(@PathVariable String code) {
        return flightService.searchFlight(code);
    }
}
