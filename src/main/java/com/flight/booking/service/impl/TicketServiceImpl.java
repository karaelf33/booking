package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.Customer;
import com.flight.booking.entity.Ticket;
import com.flight.booking.exception.CustomerProcessesException;
import com.flight.booking.exception.TicketProcessesException;
import com.flight.booking.repository.CustomerRepository;
import com.flight.booking.repository.TicketRepository;
import com.flight.booking.service.TicketService;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;
    CustomerRepository customerRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public GenericDTO buyTicket(String customerNameSurname, int ticketId) {
        final Optional<Customer> customerByNameSurname = customerRepository.findByNameSurname(customerNameSurname);
        Optional<Ticket> ticketById = null;
        try {

            ticketById = ticketRepository.findById(ticketId);

            if (!ticketById.isPresent())
                throw TicketProcessesException.buildExceptionNotFoundTicket();


            if (!customerByNameSurname.isPresent())
                throw CustomerProcessesException.buildExceptionCustomerNotfound();

            //TODO is there empty seat in flight?

            if (ticketById.get().getCustomer() != null)
                throw TicketProcessesException.buildExceptionTicketAlreadyTaken();

            ticketById.get().setCustomer(customerByNameSurname.get());
            ticketRepository.save(ticketById.get());

            setExtraPriceForTicketIfNecessary(ticketId, ticketById);


            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(ticketById.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);

        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(ticketById.get()),
                    OperationUtils.FAIL_CODE,
                    true,
                    e.getMessage());
        }

    }

    private void setExtraPriceForTicketIfNecessary(int ticketId, Optional<Ticket> ticketById) {
        int totalTicketCount = ticketById.get().getFlight().getQuota();

        int[] stepPoints = OperationUtils.getStepPoints(totalTicketCount);

        int soldTicketsCount = ticketRepository.findByCustomerNotNull().size();

        int countToPassForExtraPrice = 0;

        for (int stepPoint : stepPoints) {
            if (stepPoint >= soldTicketsCount) {
                countToPassForExtraPrice = stepPoint;
                break;
            }
        }
        if (soldTicketsCount >= countToPassForExtraPrice) {
            int rawPrice = ticketById.get().getPrice();
            final int addedPrice = rawPrice + ((rawPrice / 100) * 10);
            List<Ticket> ticketNotSold = ticketRepository.findAllByCustomerIsNull();
            for (Ticket ticket : ticketNotSold) {
                ticket.setPrice(addedPrice);
                ticketRepository.save(ticket);
            }
        }
    }

    @Override
    public GenericDTO cancelTicket(String customerNameSurname, int ticketId) {
        Optional<Ticket> ticket = null;
        final Optional<Customer> customerByNameSurname = customerRepository.findByNameSurname(customerNameSurname);
        try {
            ticket = ticketRepository.findById(ticketId);
            if (!ticket.isPresent())
                throw TicketProcessesException.buildExceptionNotFoundTicket();


            if (!customerByNameSurname.isPresent())
                throw CustomerProcessesException.buildExceptionCustomerNotfound();
            ticket.get().setCustomer(null);
            ticketRepository.save(ticket.get());
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(ticket.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(ticket.get()),
                    OperationUtils.FAIL_CODE,
                    true,
                    e.getMessage());
        }

    }

}
