package com.flight.booking.exception;

public class ResultException extends BaseException {
    public ResultException(String message) {
        super(message);
    }

    public static ResultException resultNotFound() {

        return new ResultException(ExceptionMessages.RESULT_NOT_FOUND.getMessage());
    }
}
