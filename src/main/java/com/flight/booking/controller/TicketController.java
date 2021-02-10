package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping(value = "/buyTicket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO buyTicket(@RequestParam String customerNameSurname,
                                @RequestParam int ticketId){

        return ticketService.buyTicket(customerNameSurname,ticketId);

    }

    @PostMapping(value = "/cancelTicket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO cancelTicket(@RequestParam String customerNameSurname,
                                @RequestParam int ticketId){

        return ticketService.cancelTicket(customerNameSurname,ticketId);

    }
}
