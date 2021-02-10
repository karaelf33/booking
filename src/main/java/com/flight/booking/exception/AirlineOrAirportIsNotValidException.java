package com.flight.booking.exception;

public class AirlineOrAirportIsNotValidException extends BaseException {
    public AirlineOrAirportIsNotValidException(String message) {
        super(message);
    }

    public static AirlineOrAirportIsNotValidException buildException() {

        return new AirlineOrAirportIsNotValidException(ExceptionMessages.AIRLINE_AIRPORT_RELATION_IS_NOT_VALID.getMessage());
    }
}
