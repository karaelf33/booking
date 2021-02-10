package com.flight.booking.service;

import com.flight.booking.dto.GenericDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface TicketService {

    GenericDTO buyTicket(String customerNameSurname,int ticketId);

    GenericDTO cancelTicket(String customerNameSurname, int ticketId);
}
