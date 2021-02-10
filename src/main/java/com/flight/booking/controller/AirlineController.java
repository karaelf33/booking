package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/airline")
public class AirlineController {

    @Autowired
    AirlineCompanyService airlineCompanyService;

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO addAirline(@RequestParam String name) {
        return airlineCompanyService.addAirlineCompany(name);
    }

    @GetMapping(value = "/search/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO searchAirline(@PathVariable String name) {
        return airlineCompanyService.searchAirline(name);
    }
}
