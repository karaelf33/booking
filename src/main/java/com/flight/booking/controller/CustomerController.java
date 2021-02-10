package com.flight.booking.controller;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO createCustomer(
            @RequestParam String nameSurname,
            @RequestParam String cardNumber
    ) {
        return customerService.createCustomer(nameSurname, cardNumber);
    }
}
