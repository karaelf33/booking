package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.AirlineCompany;
import com.flight.booking.exception.ResultException;
import com.flight.booking.repository.AirlineCompanyRepository;
import com.flight.booking.service.AirlineCompanyService;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    AirlineCompanyServiceImpl(AirlineCompanyRepository airlineCompanyRepository) {
        this.airlineCompanyRepository = airlineCompanyRepository;
    }


    @Override
    public GenericDTO addAirlineCompany(String name) {
        AirlineCompany airlineCompany = null;
        try {
            airlineCompany = AirlineCompany.buildAirlineCompany(name);
            airlineCompanyRepository.save(airlineCompany);
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(airlineCompany),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);


        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    airlineCompany,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }


    }

    @Override
    public GenericDTO searchAirline(String name) {
        Optional<AirlineCompany> airlineCompany = null;
        try {
            airlineCompany = airlineCompanyRepository.findByName(name);
            if (!airlineCompany.isPresent())
                throw ResultException.resultNotFound();

            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(airlineCompany.get()),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);

        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    airlineCompany,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());
        }
    }
}
