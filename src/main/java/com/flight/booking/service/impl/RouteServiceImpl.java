package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.Airport;
import com.flight.booking.entity.Route;
import com.flight.booking.exception.AirportsCantFindForDepartureOrLandingException;
import com.flight.booking.exception.LandingDateEarlierThanDepartureDateException;
import com.flight.booking.exception.ResultException;
import com.flight.booking.repository.AirportRepository;
import com.flight.booking.repository.RouteRepository;
import com.flight.booking.service.RouteService;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    RouteRepository routeRepository;
    AirportRepository airportRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository,
                            AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.airportRepository = airportRepository;

    }

    @Override
    public GenericDTO addRoute(Date departureDate,
                               Date landingDate,
                               String routeName,
                               String departureAirportName,
                               String landingAirportName) {

        Route route = null;

        try {
            if (landingDate.toInstant().isBefore(departureDate.toInstant()))
                throw LandingDateEarlierThanDepartureDateException.buildException();

            Optional<Airport> departureAirportByName = airportRepository.findByName(departureAirportName);
            Optional<Airport> landingAirportByName = airportRepository.findByName(landingAirportName);


            if (!departureAirportByName.isPresent() || !landingAirportByName.isPresent())
                throw AirportsCantFindForDepartureOrLandingException.buildException();

            route = Route.buildRoute(departureDate,
                    landingDate,
                    departureAirportByName.get(),
                    landingAirportByName.get(),
                    routeName);

            routeRepository.save(route);

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(route),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);


        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    route,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }

    @Override
    public GenericDTO searchRoute(String name) {
        Optional<Route> route = null;
        try {
            route = routeRepository.findByName(name);
            if (!route.isPresent())
                throw ResultException.resultNotFound();

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(route.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    route,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }
}
