package com.flight.booking.exception;

public class CustomerProcessesException  extends BaseException{
    public CustomerProcessesException(String message) {
        super(message);
    }

    public static TicketProcessesException buildExceptionCustomerNotfound() {

        return new TicketProcessesException(ExceptionMessages.CUSTOMER_NOT_FOUND.getMessage());
    }
}
