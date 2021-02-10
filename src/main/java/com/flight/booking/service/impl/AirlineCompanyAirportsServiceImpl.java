package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.AirlineCompany;
import com.flight.booking.entity.AirlineCompanyAirports;
import com.flight.booking.entity.Airport;
import com.flight.booking.exception.AirlineOrAirportIsNotValidException;
import com.flight.booking.repository.AirlineCompanyAirportsRepository;
import com.flight.booking.repository.AirlineCompanyRepository;
import com.flight.booking.repository.AirportRepository;
import com.flight.booking.service.AirlineCompanyAirportsService;
import com.flight.booking.utils.OperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineCompanyAirportsServiceImpl implements AirlineCompanyAirportsService {

    Logger logger = LoggerFactory.getLogger(AirlineCompanyAirportsServiceImpl.class);

    AirlineCompanyRepository airlineCompanyRepository;
    AirportRepository airportRepository;
    AirlineCompanyAirportsRepository airlineCompanyAirportsRepository;


    @Autowired
    AirlineCompanyAirportsServiceImpl(AirlineCompanyRepository airlineCompanyRepository,
                                      AirportRepository airportRepository,
                                      AirlineCompanyAirportsRepository airlineCompanyAirportsRepository) {
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.airportRepository = airportRepository;
        this.airlineCompanyAirportsRepository = airlineCompanyAirportsRepository;
    }


    @Override
    public GenericDTO addAirlineCompanyAirportsRelation(String airlineCompanyName, String airportName) {

        AirlineCompanyAirports airlineCompanyAirports = null;
        try {
            Optional<AirlineCompany> airlineCompanyByName = airlineCompanyRepository.findByName(airlineCompanyName);
            Optional<Airport> airportByName = airportRepository.findByName(airportName);
            if (!airlineCompanyByName.isPresent() || !airportByName.isPresent()) {
                throw AirlineOrAirportIsNotValidException.buildException();
            }

            airlineCompanyAirports = AirlineCompanyAirports.buildAirlineCompanyAirports(
                    airlineCompanyByName.get().getId(), airportByName.get().getId());

            airlineCompanyAirportsRepository.save(airlineCompanyAirports);

            return OperationUtils.returnMessageHandling(
                    airlineCompanyAirports,
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return OperationUtils.returnMessageHandling(
                    airlineCompanyAirports,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }
}
