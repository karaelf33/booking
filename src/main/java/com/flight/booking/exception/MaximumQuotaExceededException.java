package com.flight.booking.exception;

public class MaximumQuotaExceededException  extends BaseException{
    public MaximumQuotaExceededException(String message) {
        super(message);
    }

    public static MaximumQuotaExceededException buildException() {

        return new MaximumQuotaExceededException(ExceptionMessages.MAXIMUM_QUOTE_EXCEEDED.getMessage());
    }
}
