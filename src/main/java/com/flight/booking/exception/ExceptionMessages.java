package com.flight.booking.exception;

public enum ExceptionMessages {

    COMPANY_OR_ROUTE_CANT_FIND("Airline company or route couldn't find. Please enter valid names."),
    MAXIMUM_QUOTE_EXCEEDED("Maximum quota for a flight can not be more than 150."),
    LANDING_DEPARTURE_DATE_CONTROL("Landing date cannot be earlier than departure date."),
    DEPARTURE_OR_LANDING_AIRPORTS_CANT_FIND("Departure or landing airports couldn't find. please enter valid airports."),
    AIRLINE_AIRPORT_RELATION_IS_NOT_VALID("Airline or airport couldn't find. please enter valid name for both."),
    CREDIT_CARD_NOT_VALID("Credit card number is not valid"),
    CREDIT_CARD_LENGTH_NOT_VALID("Credit card number length is not valid."),
    NOT_FOUND_TICKET("Ticket Not Found"),
    TICKET_ALREADY_TOKEN("This ticket is already taken"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    RESULT_NOT_FOUND("Result not found")
    ;

    ExceptionMessages(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }
}
