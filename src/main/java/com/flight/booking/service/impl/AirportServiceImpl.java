package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.Airport;
import com.flight.booking.exception.ResultException;
import com.flight.booking.repository.AirportRepository;
import com.flight.booking.service.AirportService;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {


    AirportRepository airportRepository;

    @Autowired
    AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public GenericDTO addAirport(String name, String location) {

        Airport airport = null;
        try {
            airport = Airport.buildAirport(name, location);
            airportRepository.save(airport);
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(airport),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);


        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    airport,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }

    @Override
    public GenericDTO searchAirport(String name) {
        Optional<Airport> airport = null;
        try {
            airport = airportRepository.findByName(name);

            if (!airport.isPresent())
                throw ResultException.resultNotFound();

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(airport.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);

        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    airport,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }
}
