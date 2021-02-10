package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.AirlineCompany;
import com.flight.booking.entity.Flight;
import com.flight.booking.entity.Route;
import com.flight.booking.entity.Ticket;
import com.flight.booking.exception.CompanyOrRouteCantFindException;
import com.flight.booking.exception.MaximumQuotaExceededException;
import com.flight.booking.exception.ResultException;
import com.flight.booking.repository.AirlineCompanyRepository;
import com.flight.booking.repository.FlightRepository;
import com.flight.booking.repository.RouteRepository;
import com.flight.booking.repository.TicketRepository;
import com.flight.booking.service.FlightService;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import com.flight.booking.utils.SeatNumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);


    FlightRepository flightRepository;
    AirlineCompanyRepository airlineCompanyRepository;
    RouteRepository routeRepository;
    TicketRepository ticketRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository,
                             AirlineCompanyRepository airlineCompanyRepository,
                             RouteRepository routeRepository,
                             TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.routeRepository = routeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public GenericDTO createFlight(String code, int quota, String airlineCompanyName, String routeName, int initialPrice) {

        Flight flight = null;

        try {
            final Optional<AirlineCompany> airlineCompanyByName = airlineCompanyRepository.findByName(airlineCompanyName);
            final Optional<Route> routeByName = routeRepository.findByName(routeName);


            if (!airlineCompanyByName.isPresent() || !routeByName.isPresent())
                throw CompanyOrRouteCantFindException.buildException();

            if (quota <= 150) {
                flight = Flight.buildFlight(code, quota, airlineCompanyByName.get(), routeByName.get());

                flightRepository.save(flight);

                createTicketAndInitializeSeatNumbers(initialPrice, flight);

                return OperationUtils.returnMessageHandling(
                        MapperUtils.entityToHashMapMapper(flight),
                        OperationUtils.SUCCESS_CODE,
                        true,
                        OperationUtils.SUCCESS_MESSAGE);
            } else {
                throw MaximumQuotaExceededException.buildException();
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    flight,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());

        }

    }

    private void createTicketAndInitializeSeatNumbers(int initialPrice, Flight flight) {
        final List<String> seatNumbers = SeatNumberUtils.crateSeatNumbers();

        for (int i = 0; i < flight.getQuota(); i++) {
            Ticket ticket = Ticket.buildTicket(initialPrice, seatNumbers.get(i), flight);
            ticketRepository.save(ticket);

        }
    }

    @Override
    public GenericDTO searchFlight(String code) {
        Optional<Flight> flight = null;
        try {
            flight=flightRepository.findByCode(code);
            if (!flight.isPresent())
                throw ResultException.resultNotFound();

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(flight.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    flight,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());

        }
    }
}
