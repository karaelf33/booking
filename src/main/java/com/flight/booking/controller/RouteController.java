package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/route")
public class RouteController {

    @Autowired
    RouteService routeService;

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO addAirline(
            @RequestParam("departureDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date departureDate,
            @RequestParam("landingDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date landingDate,
            @RequestParam String routeName,
            @RequestParam String departureAirportName,
            @RequestParam String landingAirportName
            ){
        return  routeService.addRoute(departureDate,landingDate,routeName,departureAirportName,landingAirportName);
    }

    @GetMapping(value = "/search/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO searchFlight(@PathVariable String name) {
        return routeService.searchRoute(name);
    }
}
