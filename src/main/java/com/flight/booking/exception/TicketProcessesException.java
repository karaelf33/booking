package com.flight.booking.exception;

public class TicketProcessesException extends BaseException {
    public TicketProcessesException(String message) {
        super(message);
    }

    public static TicketProcessesException buildExceptionNotFoundTicket() {

        return new TicketProcessesException(ExceptionMessages.NOT_FOUND_TICKET.getMessage());
    }
    public static TicketProcessesException buildExceptionTicketAlreadyTaken() {

        return new TicketProcessesException(ExceptionMessages.TICKET_ALREADY_TOKEN.getMessage());
    }
}
