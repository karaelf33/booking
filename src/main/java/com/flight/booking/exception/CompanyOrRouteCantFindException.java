package com.flight.booking.exception;

public class CompanyOrRouteCantFindException extends BaseException {

    public CompanyOrRouteCantFindException(String message) {
        super(message);
    }

    public static CompanyOrRouteCantFindException buildException() {

        return new CompanyOrRouteCantFindException(ExceptionMessages.COMPANY_OR_ROUTE_CANT_FIND.getMessage());
    }

}
