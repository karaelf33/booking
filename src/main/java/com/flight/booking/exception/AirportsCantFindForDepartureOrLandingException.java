package com.flight.booking.exception;

public class AirportsCantFindForDepartureOrLandingException extends BaseException {
    public AirportsCantFindForDepartureOrLandingException(String message) {
        super(message);
    }

    public static AirportsCantFindForDepartureOrLandingException buildException() {

        return new AirportsCantFindForDepartureOrLandingException(ExceptionMessages.DEPARTURE_OR_LANDING_AIRPORTS_CANT_FIND.getMessage());
    }
}
