package com.flight.booking.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIRLINE_COMPANY_AIRPORTS")
@Data
public class AirlineCompanyAirports {

    @Id
    @Column(name = "AIRLINE_COMPANIES_ID")
    private int airlineCompaniesId;

    @Column(name = "AIRPORTS_ID")
    private int airportsId;

    public static AirlineCompanyAirports buildAirlineCompanyAirports(int airlineCompaniesId, int airportsId) {
        AirlineCompanyAirports airlineCompanyAirports = new AirlineCompanyAirports();
        airlineCompanyAirports.setAirlineCompaniesId(airlineCompaniesId);
        airlineCompanyAirports.setAirportsId(airportsId);
        return airlineCompanyAirports;
    }

}
